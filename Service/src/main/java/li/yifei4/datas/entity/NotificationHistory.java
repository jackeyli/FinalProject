package li.yifei4.datas.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="FINAL_PROJ_NOTIFICATION_HISTORY")
public class NotificationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;


    @Column(name="TRIGGERTIME")
    private Date triggerTime;

    @ManyToOne
    @JoinColumn(name = "NTOID", referencedColumnName = "OID")
    private NotificationCondition condition;

    public int getOid() {
        return oid;
    }
    public void setOid(int id) {
        this.oid = oid;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public NotificationCondition getCondition() {
        return condition;
    }

    public void setCondition(NotificationCondition condition) {
        this.condition = condition;
    }
}
