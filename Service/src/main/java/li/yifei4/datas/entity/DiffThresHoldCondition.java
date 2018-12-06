package li.yifei4.datas.entity;
import javax.persistence.*;
@Entity
@Table(name="FINAL_PROJ_DIFF_CONDITION")
public class DiffThresHoldCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;

    @Column(name="DIFF_THRESHOLD")
    private double diff_thre;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public double getDiff_thre() {
        return diff_thre;
    }

    public void setDiff_thre(double diff_thre) {
        this.diff_thre = diff_thre;
    }
}
