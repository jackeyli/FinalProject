package li.yifei4.datas.entity;

import javax.persistence.*;
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

    public List<DigitalMarketCurrencyExchangeInfo> getExchangeInfos() {
        return exchangeInfos;
    }

    public void setExchangeInfos(List<DigitalMarketCurrencyExchangeInfo> exchangeInfos) {
        this.exchangeInfos = exchangeInfos;
    }
}
