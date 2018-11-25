package li.yifei4.services;

import li.yifei4.catcher.extractor.JsonExtractor;
import li.yifei4.datas.dao.DigitalMarketCurrencyDao;
import li.yifei4.datas.dao.DigitalMarketDao;
import li.yifei4.datas.entity.DigitalMarket;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import li.yifei4.datas.entity.DigitalMarketCurrencyExchangeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Transactional
@Service("currencyStockService")
public class DefaultCurrencyStockServiceImpl implements CurrencyService{
    @Resource(name="digitalMarketCurrencyDao")
    private DigitalMarketCurrencyDao digitalMarketCurrencyDao;
    @Resource(name="digitalMarketDao")
    private DigitalMarketDao digitalMarketDao;
    public void storeCurrencyMarket(List<String> tradePlces) {
        for(String str : tradePlces){
            JsonExtractor extractor = JsonExtractor.create(str);
            List<DigitalMarketCurrency> markets = extractor.getCurrencyMarket();
            for(DigitalMarketCurrency market : markets) {
                digitalMarketCurrencyDao.persist(market);
            }
        }
    }
    public List<DigitalMarketCurrency> getCurrencyMarkets(String marketPlc) {
        DigitalMarket market = digitalMarketDao.find(marketPlc);
        List<DigitalMarketCurrencyExchangeInfo> infos = market.getExchangeInfos();
        List<DigitalMarketCurrency> result = new ArrayList<DigitalMarketCurrency>();
        for(DigitalMarketCurrencyExchangeInfo info : infos) {
            result.add(digitalMarketCurrencyDao.getCurrencyMarkets(info.getRealCurrency(),info.getName(),marketPlc));
        }
        return result;
    }
    public HashMap<String,List<DigitalMarketCurrency>> getAllCurrencies() {
        List<DigitalMarket> markets = digitalMarketDao.findAll();
        HashMap<String,List<DigitalMarketCurrency>> hshMap = new HashMap<String,List<DigitalMarketCurrency>>();
        for(DigitalMarket market : markets) {
            hshMap.put(market.getName(),this.getCurrencyMarkets(market.getName()));
        }
        return hshMap;
    }
}
