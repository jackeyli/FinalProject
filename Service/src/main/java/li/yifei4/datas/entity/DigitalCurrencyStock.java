package li.yifei4.datas.entity;
import javax.persistence.*;
@Entity
@Table(name="FINAL_PROJ_DIGIT_CURRENCY")
public class DigitalCurrencyStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;
    @Column(name="CURRENCY_NAME")
    private String name;
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
}