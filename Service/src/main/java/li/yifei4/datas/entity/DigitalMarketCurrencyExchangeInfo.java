package li.yifei4.datas.entity;

import javax.persistence.*;

@Entity
@Table(name="FINAL_PROJ_MPLACE_PROVIDE_CURRENCY_EXCH")
public class DigitalMarketCurrencyExchangeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;
    @Column(name="CURRENCY_NAME")
    private String name;
    @Column(name="REAL_CURRENCY_NAME")
    private String realCurrency;
    @ManyToOne
    @JoinColumn(name = "MARKET_PLACE_OID", referencedColumnName = "OID")
    private DigitalMarket digitalMarket;

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
    public String getRealCurrency() {
        return realCurrency;
    }
    public void setRealCurrency(String realCurrency) {
        this.realCurrency = realCurrency;
    }
    public DigitalMarket getDigitalMarket() {
        return digitalMarket;
    }
    public void setDigitalMarket(DigitalMarket digitalMarket) {
        this.digitalMarket = digitalMarket;
    }
}
