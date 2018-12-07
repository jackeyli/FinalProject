package li.yifei4.services;

import li.yifei4.beans.ConditionCheckResult;
import li.yifei4.datas.entity.NotificationHistory;

import java.util.Date;
import java.util.List;

public interface NotificationService {
    boolean storeHistoryAndNotifiyUser (ConditionCheckResult result);
    List<NotificationHistory> getNotificationHistory(int userId, Date from, Date to);
}
