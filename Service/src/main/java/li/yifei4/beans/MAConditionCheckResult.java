package li.yifei4.beans;

import li.yifei4.datas.entity.NotificationCondition;

public class MAConditionCheckResult extends ConditionCheckResult{
    private String currencyName;
    private String marketPlc;
    private String interval;
    private int ma_1;
    private int ma_2;
    private String direction;
    public static final String DIRECTION_UP = "UP";
    public static final String DIRECTION_DOWN = "DOWN";
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getMarketPlc() {
        return marketPlc;
    }

    public void setMarketPlc(String marketPlc) {
        this.marketPlc = marketPlc;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public int getMa_1() {
        return ma_1;
    }

    public void setMa_1(int ma_1) {
        this.ma_1 = ma_1;
    }

    public int getMa_2() {
        return ma_2;
    }

    public void setMa_2(int ma_2) {
        this.ma_2 = ma_2;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNotificationText(){
        return "test2";
    }
}
