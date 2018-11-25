package li.yifei4.catcher.extractor;


import li.yifei4.datas.entity.DigitalMarketCurrency;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public interface JsonExtractor {
    default List<AbstractMap.SimpleEntry> getAllCurrLists() {
        List<AbstractMap.SimpleEntry> lst = new ArrayList();
        lst.add(new AbstractMap.SimpleEntry("BTC","USDT"));
        lst.add(new AbstractMap.SimpleEntry("ETH","USDT"));
        lst.add(new AbstractMap.SimpleEntry("BCH","USDT"));
        lst.add(new AbstractMap.SimpleEntry("LTC","USDT"));
        lst.add(new AbstractMap.SimpleEntry("ETC","USDT"));
        return lst;
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
