package li.yifei4.datas.dao;

import li.yifei4.datas.entity.NotificationHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository("notificationHistoryDao")
public class NotificationHistoryDao extends EntityDao<NotificationHistory> {
    public List<NotificationHistory> getNotificationHistory(int userId, Date from ,Date to){
        MyCriteriaBuilder<NotificationHistory> builder = this.getCriteriaBuilder();
        Predicate user = builder.predicate("equal",
                builder.get("condition").get("user").get("oid"),userId);
        Predicate dateRange = builder.predicate("between",
                builder.get("triggerTime"),from, Optional.ofNullable(to).orElse(new Date()));
        builder.where(builder.and(user,dateRange));
        return builder.createExecutiveQuery().getResultList();
    }
}
