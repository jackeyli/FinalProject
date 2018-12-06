package li.yifei4.datas.entity;
import javax.persistence.*;
@Entity
@Table(name="FINAL_PROJ_MA_CONDITION")
public class MANotificationCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;

    @Column(name="MA_ARG_1")
    private int MA_ARG_1;

    @Column(name="MA_ARG_2")
    private int MA_ARG_2;

    @Column(name="MA_DIRECTION")
    private String direction;

    @Column(name="MA_INTERVAL")
    private String interval;

    @Column(name="MARKET_PLACE")
    private String marketPlace;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public int getMA_ARG_1() {
        return MA_ARG_1;
    }

    public void setMA_ARG_1(int MA_ARG_1) {
        this.MA_ARG_1 = MA_ARG_1;
    }

    public int getMA_ARG_2() {
        return MA_ARG_2;
    }

    public void setMA_ARG_2(int MA_ARG_2) {
        this.MA_ARG_2 = MA_ARG_2;
    }

    public String getMarketPlace() {
        return marketPlace;
    }

    public void setMarketPlace(String marketPlace) {
        this.marketPlace = marketPlace;
    }
}
