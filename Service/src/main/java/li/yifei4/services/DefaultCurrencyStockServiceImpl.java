package li.yifei4.services;

import li.yifei4.beans.CurrencyPriceQueryBean;
import li.yifei4.catcher.extractor.JsonExtractor;
import li.yifei4.datas.dao.DigitalMarketCurrencyDao;
import li.yifei4.datas.dao.DigitalMarketDao;
import li.yifei4.datas.entity.DigitalMarket;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service("currencyStockService")
public class DefaultCurrencyStockServiceImpl implements CurrencyService{
    @Resource(name="digitalMarketCurrencyDao")
    private DigitalMarketCurrencyDao digitalMarketCurrencyDao;
    @Resource(name="digitalMarketDao")
    private DigitalMarketDao digitalMarketDao;
    public boolean storeCurrencyMarket() {
        List<DigitalMarket> markets = digitalMarketDao.getAllSupportedMarket();
        for(DigitalMarket mkt : markets){
            JsonExtractor extractor = JsonExtractor.create(mkt.getName());
            List<DigitalMarketCurrency> marketCurrencies = extractor.getCurrencyMarket();
            for(DigitalMarketCurrency currency : marketCurrencies) {
                digitalMarketCurrencyDao.persist(currency);
            }
        }
        return true;
    }
    public List<DigitalMarketCurrency> getCurrencyMarkets() {
        List<DigitalMarket> markets = digitalMarketDao.findAll();
        List<DigitalMarketCurrency> currencies = new ArrayList<DigitalMarketCurrency>();
        for(DigitalMarket market : markets){
            currencies.addAll(market.getCurrencyMarkets());
        }
        return currencies;
    }
    public HashMap<String,List<DigitalMarketCurrency>> getAllCurrencies() {
        List<DigitalMarket> markets = digitalMarketDao.findAll();
        HashMap<String,List<DigitalMarketCurrency>> hshMap = new HashMap<String,List<DigitalMarketCurrency>>();
        for(DigitalMarket market : markets) {
            hshMap.put(market.getName(),market.getCurrencyMarkets());
        }
        return hshMap;
    }
    public List currencyPriceHistory(CurrencyPriceQueryBean bean){
        return digitalMarketCurrencyDao.queryForPrices(bean.getMarket(),bean.getName(),bean.getInterval());
    }
}
