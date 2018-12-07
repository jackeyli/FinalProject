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
    @Column(name="NTOID")
    private int ntOid;
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

    public int getNtOid() {
        return ntOid;
    }

    public void setNtOid(int ntOid) {
        this.ntOid = ntOid;
    }
}
