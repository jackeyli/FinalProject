package li.yifei4.services;

import li.yifei4.datas.entity.DigitalMarketCurrency;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface CurrencyService   {
     void storeCurrencyMarket(String tradePlc);
     List<DigitalMarketCurrency> getCurrencyMarkets(String marketPlc);
     HashMap<String,List<DigitalMarketCurrency>> getAllCurrencies();
}
