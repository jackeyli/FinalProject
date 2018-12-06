package li.yifei4.beans;

import li.yifei4.datas.entity.NotificationCondition;

public class ConditionCheckResult {
    private boolean pass;

    private NotificationCondition condition;
    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public NotificationCondition getCondition() {
        return condition;
    }

    public void setCondition(NotificationCondition condition) {
        this.condition = condition;
    }
}
