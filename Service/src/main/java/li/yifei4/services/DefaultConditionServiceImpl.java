package li.yifei4.services;

import li.yifei4.beans.*;
import li.yifei4.datas.dao.*;
import li.yifei4.datas.entity.*;
import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("conditionService")
public class DefaultConditionServiceImpl implements ConditionService{

    @Resource(name="notificationConditionDao")
    private NotificationConditionDao notificationConditionDao;

    @Resource(name="mANotificationConditionDao")
    private MANotificationConditionDao mANotificationConditionDao;

    @Resource(name="diffThresHoldConditionDao")
    private DiffThresHoldConditionDao diffThresHoldConditionDao;

    @Resource(name="currencyStockService")
    private DefaultCurrencyStockServiceImpl defaultCurrencyStockService;

    @Resource(name="digitalMarketDao")
    private DigitalMarketDao digitalMarketDao;

    @Resource(name="digitalMarketCurrencyDao")
    private DigitalMarketCurrencyDao digitalMarketCurrencyDao;

    @Override
    public List<ConditionCheckResult> getPassedConditionThisRound() {
        List<NotificationCondition> allConditions = notificationConditionDao.findAll();
        List<ConditionCheckResult> passedConditions = new ArrayList<ConditionCheckResult>();
        for(NotificationCondition notiCond : allConditions){
            switch(notiCond.getType()){
                case NotificationCondition.CONDITION_TYPE_MA:
                    MANotificationCondition maCondi = EntityManagerUtil.getEntityManager()
                            .find(MANotificationCondition.class,notiCond.getSubCondiOid());
                    MAConditionCheckResult result = isMAConditionPass(maCondi,notiCond);
                    if(result.isPass())
                        passedConditions.add(result);
                    break;
                case NotificationCondition.CONDITION_TYPE_DIFFTHRESHOLD:
                    DiffThresHoldCondition diffCond = EntityManagerUtil.getEntityManager()
                            .find(DiffThresHoldCondition.class,notiCond.getSubCondiOid());
                    DiffThresHoldCheckResult dResult = isThresHoldConditionPass(diffCond,notiCond);
                    if(dResult.isPass())
                        passedConditions.add(dResult);
                    break;
                default:
                    break;
            }
        }
        return passedConditions;
    }

    public List<NotificationCondition> getNotificationCondition(int userId){
        return notificationConditionDao.getUserNotifications(userId);
    }

    public boolean removeNotificationCondition(int oid){
        NotificationCondition notification = EntityManagerUtil.getEntityManager().find(NotificationCondition.class,oid);
        if(notification != null)
            EntityManagerUtil.getEntityManager().remove(notification);
        return true;
    }

    public boolean storeNotification(NotificationBean bean){
        NotificationCondition condition = new NotificationCondition();
        condition.setUserOid(bean.getUserOid());
        condition.setNotifyType(bean.getNotifyType());
        condition.setName(bean.getCurrencyName());
        condition.setType(bean.getType());
        if(Objects.equals(bean.getType(),NotificationCondition.CONDITION_TYPE_DIFFTHRESHOLD)){
            DiffThresHoldCondition diffThreCondi = new DiffThresHoldCondition();
            diffThreCondi.setDiff_thre(bean.getDiffThre());
            EntityManagerUtil.getEntityManager().persist(diffThreCondi);
            condition.setSubCondiOid(diffThreCondi.getOid());
        } else {
            MANotificationCondition maCondi = new MANotificationCondition();
            maCondi.setDirection(bean.getDirection());
            maCondi.setInterval(bean.getInterval());
            maCondi.setMA_ARG_1(bean.getMa_arg_1());
            maCondi.setMA_ARG_2(bean.getMa_arg_2());
            maCondi.setMarketPlace(bean.getMa_market_place());
            EntityManagerUtil.getEntityManager().persist(maCondi);
            condition.setSubCondiOid(maCondi.getOid());
        }
        EntityManagerUtil.getEntityManager().persist(condition);
        return true;
    }

    public MAConditionCheckResult isMAConditionPass(MANotificationCondition maCondi,NotificationCondition notiCond){
        MAConditionCheckResult result = new MAConditionCheckResult();
        result.setCondition(notiCond);
        String interval = maCondi.getInterval();
        String direction = maCondi.getDirection();
        int ma_arg_1 = maCondi.getMA_ARG_1();
        int ma_arg_2 = maCondi.getMA_ARG_2();
        String currency = notiCond.getName();
        String marketPlc = maCondi.getMarketPlace();
        List<CurrencyPriceBean> prices = digitalMarketCurrencyDao
                .queryForPrices(marketPlc,currency,interval);
        double maCur_1 = 0;
        double maLst_1 = 0;
        int countCur_1 = 0;
        int countLst_1 = 0;
        double maCur_2 = 0;
        double maLst_2 = 0;
        int countCur_2 = 0;
        int countLst_2 = 0;
        for(int i = prices.size() - 1; i >=0 && countLst_2 < ma_arg_2; i --){
            double curPrice = prices.get(i).getClose();
            if(i != prices.size() - 1){
                if(countLst_1 < ma_arg_1){
                    maLst_1 += curPrice;
                    countLst_1 ++;
                }
                maLst_2 += curPrice;
                countLst_2 ++;
            }
            if(countCur_1 < ma_arg_1){
                maCur_1 += curPrice;
                countCur_1 ++;
            }
            if(countCur_2 < ma_arg_2){
                maCur_2 += curPrice;
                countCur_2 ++;
            }
        }
        double avgCur_1 = maCur_1 / countCur_1;
        double avgCur_2 = maCur_2 / countCur_2;
        double avgLst_1 = maLst_1 / countLst_1;
        double avgLst_2 = maLst_2 / countLst_2;
        if(Objects.equals(direction,MAConditionCheckResult.DIRECTION_UP)){
            if(avgLst_1 < avgLst_2 && avgCur_1 >= avgCur_2){
                result.setPass(true);
                result.setCurrencyName(currency);
                result.setInterval(interval);
                result.setMa_1(ma_arg_1);
                result.setMa_2(ma_arg_2);
                result.setMarketPlc(marketPlc);
                result.setDirection(MAConditionCheckResult.DIRECTION_UP);
                return result;
            }
        }
        if(Objects.equals(direction,MAConditionCheckResult.DIRECTION_DOWN)){
            if(avgLst_1 >= avgLst_2 && avgCur_1 < avgCur_2){
                result.setPass(true);
                result.setCurrencyName(currency);
                result.setInterval(interval);
                result.setMa_1(ma_arg_1);
                result.setMa_2(ma_arg_2);
                result.setMarketPlc(marketPlc);
                result.setDirection(MAConditionCheckResult.DIRECTION_DOWN);
                return result;
            }
        }
        result.setPass(false);
        return result;
    }

    private DiffThresHoldCheckResult isThresHoldConditionPass(DiffThresHoldCondition diffCond,NotificationCondition notiCond){
        DiffThresHoldCheckResult diffResult = new DiffThresHoldCheckResult();
        diffResult.setCondition(notiCond);
        double thre = diffCond.getDiff_thre();
        String currency = notiCond.getName();
        List<DigitalMarketCurrency> relatedCurrencies = defaultCurrencyStockService
                .getCurrencyMarkets().stream()
                .filter((t)->{
                    return Objects.equals(t.getName(),currency);
                }).collect(Collectors.toList());
        DigitalMarketCurrency min = null;
        DigitalMarketCurrency max = null;
        for(DigitalMarketCurrency cur : relatedCurrencies){
            if(min == null)
                min = cur;
            if(max == null)
                max = cur;
            if(min.getPrice() > cur.getPrice())
                min = cur;
            if(max.getPrice() < cur.getPrice())
                max = cur;
        }
        if(min != null && max != null){
            double curDiff = Math.abs((max.getPrice() - min.getPrice()) / (min.getPrice()));
            if(curDiff >= thre)
            {
                diffResult.setPass(true);
                diffResult.setCurrencyName(currency);
                diffResult.setMarketPlace_1(min.getMarketPlcName());
                diffResult.setMarketPlace_2(max.getMarketPlcName());
                diffResult.setDiff(curDiff);
            } else {
                diffResult.setPass(false);
            }
        } else {
            diffResult.setPass(false);
        }
        return diffResult;
    }
}
