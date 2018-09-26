package li.yifei4.datas.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="FINAL_PROJ_DIGIT_CURRENCY_MARKET")
public class DigitalMarketCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;
    @Column(name="CURRENCY_NAME")
    private String name;
    @Column(name="CURRENCY_PRICE")
    private double price;
    @Column(name="TIMESTAMP")
    private String timeStamp;
    @Column(name="REAL_CURRENCY")
    private String currency;
    @Column(name="MARKET_PLACE_NAME")
    private String marketPlcName;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMarketPlcName() {
        return marketPlcName;
    }
    public void setMarketPlcName(String marketPlcName) {
        this.marketPlcName = marketPlcName;
    }
}