package li.yifei4.datas.entity;
import javax.management.Notification;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="FINAL_PROJ_USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OID")
    private int oid;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private String phone;

    @Column(name="PASSWORD")
    private String password;

    @OneToMany(mappedBy="user",fetch= FetchType.LAZY)
    private List<NotificationCondition> conditions;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<NotificationCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<NotificationCondition> conditions) {
        this.conditions = conditions;
    }
}
