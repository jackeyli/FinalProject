package li.yifei4.datas.entity;

import li.yifei4.datas.dao.DigitalMarketCurrencyDao;
import li.yifei4.datas.dao.DigitalMarketDao;
import li.yifei4.util.ApplicationContextHolder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="FINAL_PROJ_MARKET_PLACES")
public class DigitalMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;
    @Column(name="MARKET_PLACE_NAME")
    private String name;
    @OneToMany(mappedBy="digitalMarket")
    private List<DigitalMarketCurrencyExchangeInfo> exchangeInfos;
    public int getOid() {
        return oid;
    }
    public void setOid(int id) {
        this.oid = oid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void public List<DigitalMarketCurrency> getCurrencyMarkets() {
        DigitalMarketCurrencyDao digitalMarketCurrencyDao = (DigitalMarketCurrencyDao) ApplicationContextHolder.
                getApplicationContext().getBean("digitalMarketCurrencyDao");
        List<DigitalMarketCurrencyExchangeInfo> infos = this.getExchangeInfos();
        List<DigitalMarketCurrency> result = new ArrayList<DigitalMarketCurrency>();
        for(DigitalMarketCurrencyExchangeInfo info : infos) {
            result.add(digitalMarketCurrencyDao.getCurrencyMarkets(info.getRealCurrency(),info.getName(),this.getName()));
        }
        return result;
    }
    public List<DigitalMarketCurrencyExchangeInfo> getExchangeInfos() {
        return exchangeInfos;
    }

    public void setExchangeInfos(List<DigitalMarketCurrencyExchangeInfo> exchangeInfos) {
        this.exchangeInfos = exchangeInfos;
    }
}
