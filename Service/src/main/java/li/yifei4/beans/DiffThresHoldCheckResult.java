package li.yifei4.beans;

public class DiffThresHoldCheckResult extends ConditionCheckResult{

    private String marketPlace_1;
    private String marketPlace_2;
    private String currencyName;
    private double diff;
    private double price_1;
    private double price_2;

    public String getMarketPlace_1() {
        return marketPlace_1;
    }

    public void setMarketPlace_1(String marketPlace_1) {
        this.marketPlace_1 = marketPlace_1;
    }

    public String getMarketPlace_2() {
        return marketPlace_2;
    }

    public void setMarketPlace_2(String marketPlace_2) {
        this.marketPlace_2 = marketPlace_2;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public String getNotificationText(){
        return "The currency " + this.currencyName + " triggers alert, got prices:" + this.marketPlace_1 + "("
         + this.price_1 + "), " + this.marketPlace_2 + " (" + this.price_2 + "). with diff " +
                String.format("%.2f",this.diff * 100) + "%";
    }

    public double getPrice_1() {
        return price_1;
    }

    public void setPrice_1(double price_1) {
        this.price_1 = price_1;
    }

    public double getPrice_2() {
        return price_2;
    }

    public void setPrice_2(double price_2) {
        this.price_2 = price_2;
    }
}
