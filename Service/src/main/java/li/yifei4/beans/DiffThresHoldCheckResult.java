package li.yifei4.beans;

public class DiffThresHoldCheckResult extends ConditionCheckResult{

    private String marketPlace_1;
    private String marketPlace_2;
    private String currencyName;
    private double diff;

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
}
