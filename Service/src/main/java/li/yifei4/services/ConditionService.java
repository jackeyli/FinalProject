package li.yifei4.services;

import li.yifei4.beans.ConditionCheckResult;
import li.yifei4.beans.NotificationBean;
import li.yifei4.datas.entity.NotificationCondition;

import java.util.List;

public interface ConditionService  {
    List<ConditionCheckResult> getPassedConditionThisRound();
    boolean storeNotification(NotificationBean bean);
    List<NotificationCondition> getNotificationCondition(int userId);
    boolean removeNotificationCondition(int oid);
}
