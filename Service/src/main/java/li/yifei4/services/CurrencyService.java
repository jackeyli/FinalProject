package li.yifei4.services;

import li.yifei4.datas.entity.DigitalMarketCurrency;
import java.util.HashMap;
import java.util.List;

public interface CurrencyService   {
     void storeCurrencyMarket(List<String> tradePlces);
     List<DigitalMarketCurrency> getCurrencyMarkets(String marketPlc);
     HashMap<String,List<DigitalMarketCurrency>> getAllCurrencies();
}
