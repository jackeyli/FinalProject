package li.yifei4.datas.dao;
import li.yifei4.beans.CurrencyPriceBean;
import li.yifei4.datas.entity.*;
import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("digitalMarketCurrencyDao")
public class DigitalMarketCurrencyDao extends EntityDao<DigitalMarketCurrency>{
    public DigitalMarketCurrency getCurrencyMarkets(String realCurrency,String currencyName,String marketPlc){
        MyCriteriaBuilder<DigitalMarketCurrency> mcb = this.getCriteriaBuilder();
        mcb.where(mcb.and(mcb.predicate("equal",mcb.get("name"),currencyName),
                mcb.predicate("equal",mcb.get("currency"),realCurrency),
                mcb.predicate("equal",mcb.get("marketPlcName"),marketPlc)))
                .orderBy(mcb.desc(mcb.get("timeStamp")));
        return mcb.createExecutiveQuery().setFirstResult(0).setMaxResults(1).getSingleResult();
    }
    public List<CurrencyPriceBean> queryForPrices(String marketPlc, String currency, String period){
        String sql = CurrencyQuery.generateSqlForPrice(period);
        return (List<CurrencyPriceBean>) EntityManagerUtil.getEntityManager()
                .createNativeQuery(sql).setParameter("market",marketPlc)
                .setParameter("currency",currency)
                .getResultStream().map((t)->{
                    CurrencyPriceBean bean = new CurrencyPriceBean();
                    bean.setName(currency);
                    bean.setMarketName(marketPlc);
                    bean.setHighest(Double.valueOf(((Object[])t)[0].toString()));
                    bean.setLowest(Double.valueOf(((Object[])t)[1].toString()));
                    bean.setClose(Double.valueOf(((Object[])t)[2].toString()));
                    bean.setOpen(Double.valueOf(((Object[])t)[3].toString()));
                    bean.setTime(((Object[])t)[4].toString());
                    return bean;
                }).collect(Collectors.toList());
    }
}
