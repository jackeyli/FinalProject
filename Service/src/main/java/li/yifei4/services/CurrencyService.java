package li.yifei4.services;

import li.yifei4.beans.CurrencyPriceQueryBean;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import java.util.HashMap;
import java.util.List;

public interface CurrencyService   {
     void storeCurrencyMarket();
     List<DigitalMarketCurrency> getCurrencyMarkets();
     HashMap<String,List<DigitalMarketCurrency>> getAllCurrencies();
     List currencyPriceHistory(CurrencyPriceQueryBean bean);
}
