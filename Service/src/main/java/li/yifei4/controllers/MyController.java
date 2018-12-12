package li.yifei4.controllers;
import li.yifei4.beans.*;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import li.yifei4.datas.entity.NotificationCondition;
import li.yifei4.services.ConditionService;
import li.yifei4.services.CurrencyService;
import li.yifei4.services.NotificationService;
import li.yifei4.util.UserAgent;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
@Controller
@RequestMapping("/test")
public class MyController {
    @Resource(name="currencyStockService")
    private CurrencyService myService;

    @Resource(name="conditionService")
    private ConditionService conditionService;

    @Resource(name="notificationService")
    private NotificationService notificationService;

    @RequestMapping("/myProcess")
    @ResponseBody
    public ResponseDTO myProcess() throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try {
            myService.storeCurrencyMarket();
            resp.setContent("success");
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
    @RequestMapping("/getCurrencies")
    @ResponseBody
    public ResponseDTO getCurrencies() throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try {
             resp.setContent(myService.getCurrencyMarkets());
        }catch (Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
    @RequestMapping("/currencyPriceHistory")
    @ResponseBody
    public ResponseDTO getCurrencyPriceHistory(@RequestBody CurrencyPriceQueryBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            resp.setContent(myService.currencyPriceHistory(bean));
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally{
            return resp;
        }
    }

    @RequestMapping("/storeNotiCondition")
    @ResponseBody
    public ResponseDTO  storeNotiCondition(@RequestBody NotificationBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        bean.setUserOid(UserAgent.INSTANCE.getInstance().getUserID());
        try{
            resp.setContent(conditionService.storeNotification(bean));
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }

    @RequestMapping("/notiCondition")
    @ResponseBody
    public ResponseDTO  getNotiCondition() throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        int userId = UserAgent.INSTANCE.getInstance().getUserID();
        try{
            resp.setContent(conditionService.getNotificationCondition(userId).stream()
            .map((t)->{
                NotificationConditionBean bean = new NotificationConditionBean();
                bean.setName(t.getName());
                bean.setNotifyType(t.getNotifyType());
                bean.setType(t.getType());
                bean.setOid(t.getOid());
                return bean;
            }).collect(Collectors.toList()));
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }

    @RequestMapping("/delNotiCondition")
    @ResponseBody
    public ResponseDTO  deleteNotiCondition(@RequestParam int id) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            conditionService.removeNotificationCondition(id);
            resp.setContent("success");
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }

    @RequestMapping("/notificationHistory")
    @ResponseBody
    public ResponseDTO  notificationHistory(@RequestBody NotificationHistoryQueryBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bean.getDateFrom());
            Date to = StringUtils.isEmpty(bean.getDateTo()) ?
                    new Date() : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bean.getDateTo());
            List<NotificationHistoryBean> result =
                    notificationService.getNotificationHistory(UserAgent.INSTANCE.getInstance().getUserID(),from,to)
                    .stream().map((t)->{
                        NotificationHistoryBean newBean = new NotificationHistoryBean();
                        newBean.setCurrencyName(t.getCondition().getName());
                        newBean.setNotifyType(t.getCondition().getNotifyType());
                        newBean.setOid(t.getOid());
                        newBean.setTriggerTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t.getTriggerTime()));
                        newBean.setType(t.getCondition().getType());
                        return newBean;
                    }).collect(Collectors.toList());
            resp.setContent(result);
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
}
