package li.yifei4.datas.dao;

import li.yifei4.datas.entity.NotificationCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("notificationConditionDao")
public class NotificationConditionDao extends EntityDao<NotificationCondition> {
    public List<NotificationCondition> getUserNotifications(int userId){
        MyCriteriaBuilder<NotificationCondition> builder = this.getCriteriaBuilder();
        builder.where(builder.predicate("equal",builder.get("userOid"),userId));
        return builder.createExecutiveQuery().getResultList();
    }
}
