package li.yifei4.datas.dao;
import li.yifei4.datas.entity.*;
import org.springframework.stereotype.Repository;

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
}
