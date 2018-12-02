package li.yifei4.catcher.extractor;


import javafx.application.Application;
import li.yifei4.datas.dao.DigitalMarketDao;
import li.yifei4.datas.entity.DigitalMarket;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import li.yifei4.util.ApplicationContextHolder;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface JsonExtractor {
    default List<AbstractMap.SimpleEntry> getAllCurrLists() {
        DigitalMarketDao dao = (DigitalMarketDao) ApplicationContextHolder
                .getApplicationContext().getBean("digitalMarketDao");
        String marketPlaceName = this.getClass().getSimpleName().replace("Extractor","");
        DigitalMarket market = dao.find(marketPlaceName);
        return market.getExchangeInfos().stream().map((t)->new AbstractMap.SimpleEntry(t.getName(),t.getRealCurrency()))
                .collect(Collectors.toList());
    }
    static JsonExtractor create(String marketplace){
        Class clz = null;
        try {
            clz = Class.forName("li.yifei4.catcher.extractor." + marketplace + "Extractor" );
            return (JsonExtractor)clz.newInstance();
        } catch (Throwable e) {
            RuntimeException re = new RuntimeException(e.getMessage());
            re.setStackTrace(e.getStackTrace());
            throw re;
        }
    }
    List<DigitalMarketCurrency> getCurrencyMarket();
}
