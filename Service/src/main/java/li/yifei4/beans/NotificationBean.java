package li.yifei4.beans;

public class NotificationBean {

    private int userOid;
    private String type;
    private String currencyName;
    private String notifyType;
    private int ma_arg_1;
    private int ma_arg_2;
    private String ma_market_place;
    private String interval;
    private double diffThre;
    private String direction;
    public int getUserOid() {
        return userOid;
    }

    public void setUserOid(int userOid) {
        this.userOid = userOid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public int getMa_arg_1() {
        return ma_arg_1;
    }

    public void setMa_arg_1(int ma_arg_1) {
        this.ma_arg_1 = ma_arg_1;
    }

    public int getMa_arg_2() {
        return ma_arg_2;
    }

    public void setMa_arg_2(int ma_arg_2) {
        this.ma_arg_2 = ma_arg_2;
    }

    public String getMa_market_place() {
        return ma_market_place;
    }

    public void setMa_market_place(String ma_market_place) {
        this.ma_market_place = ma_market_place;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public double getDiffThre() {
        return diffThre;
    }

    public void setDiffThre(double diffThre) {
        this.diffThre = diffThre;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
