package li.yifei4.job;

import li.yifei4.beans.ConditionCheckResult;
import li.yifei4.services.ConditionService;
import li.yifei4.services.NotificationService;
import li.yifei4.util.ApplicationContextHolder;

import java.util.List;

public class NotificationProcess implements Runnable{
    private ConditionCheckResult result;
    public NotificationProcess(ConditionCheckResult result){
        this.result = result;
    }
    @Override
    public void run() {
        ConditionService conditionService =
        ((ConditionService) ApplicationContextHolder
                .getApplicationContext().getBean("conditionService"));
        NotificationService notificationService = ((NotificationService) ApplicationContextHolder
                .getApplicationContext().getBean("notificationService"));
        List<ConditionCheckResult> passedConditions = conditionService.getPassedConditionThisRound();
        for(ConditionCheckResult result : passedConditions){
            try {
                notificationService.storeHistoryAndNotifiyUser(result);
            }catch(Throwable t){

            }
        }

    }
}
