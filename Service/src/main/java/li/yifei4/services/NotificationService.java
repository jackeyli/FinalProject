package li.yifei4.services;

import li.yifei4.beans.ConditionCheckResult;

public interface NotificationService {
    boolean storeHistoryAndNotifiyUser (ConditionCheckResult result);
}
