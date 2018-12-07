package li.yifei4.datas.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="FINAL_PROJ_NOTIFICATION_CONDITION")
public class NotificationCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;

    @ManyToOne
    @JoinColumn(name = "USER_OID", referencedColumnName = "OID")
    private User user;

    @OneToMany(mappedBy="condition",fetch= FetchType.LAZY)
    private List<NotificationHistory> history;

    @Column(name="TYPE")
    private String type;

    @Column(name="CURRENCY_NAME")
    private String name;

    @Column(name="NOTIFY_TYPE")
    private String notifyType;

    @Column(name="SUBCONDITION_OID")
    private int subCondiOid;

    public static final String CONDITION_TYPE_MA = "MA";
    public static final String CONDITION_TYPE_DIFFTHRESHOLD = "DIFFTHRESHOLD";

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public int getSubCondiOid() {
        return subCondiOid;
    }

    public void setSubCondiOid(int subCondiOid) {
        this.subCondiOid = subCondiOid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NotificationHistory> getHistory() {
        return history;
    }

    public void setHistory(List<NotificationHistory> history) {
        this.history = history;
    }
}