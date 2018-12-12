package li.yifei4.datas.dao;

import li.yifei4.datas.entity.NotificationHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Repository("notificationHistoryDao")
public class NotificationHistoryDao extends EntityDao<NotificationHistory> {
    public List<NotificationHistory> getNotificationHistory(int userId, Date from ,Date to){
        MyCriteriaBuilder<NotificationHistory> builder = this.getCriteriaBuilder();
        Predicate user = builder.predicate("equal",
                builder.get("condition").get("user").get("oid"),userId);
        Predicate greaterThan = builder.predicate("greaterThan",builder.get("triggerTime"),
                from);
        Predicate lessThan = builder.predicate("lessThan",builder.get("triggerTime"),
                to);
        builder.where(builder.and(user,greaterThan,lessThan));
        return builder.createExecutiveQuery().getResultList();
    }
}
