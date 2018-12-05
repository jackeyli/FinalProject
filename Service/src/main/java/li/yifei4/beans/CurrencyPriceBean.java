package li.yifei4.beans;

public class CurrencyPriceBean {
    private String name;
    private String period;
    private double highest;
    private double lowest;
    private double open;
    private double close;
    private String marketName;
    private String time;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getHighest() {
        return highest;
    }

    public void setHighest(double highest) {
        this.highest = highest;
    }

    public double getLowest() {
        return lowest;
    }

    public void setLowest(double lowest) {
        this.lowest = lowest;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
