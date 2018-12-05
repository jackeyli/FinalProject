package li.yifei4.datas.dao;

public class CurrencyQuery {
    private static final String queryFor15Minutes = "\n" +
            "select \n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE desc),',',1) as highest,\n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE asc),',',1) as lowest,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP desc),',',1) as close,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP asc),',',1) as open,\n" +
            "concat(DATE_FORMAT(TIMESTAMP,'%m-%d-%Y %h:'),floor(date_format (TIMESTAMP,'%i') / 15) * 15) as TIME\n" +
            "from FINAL_PROJ_DIGIT_CURRENCY_MARKETS where MARKET_PLACE_NAME = :market and CURRENCY_NAME = :currency \n" +
            "group by \n" +
            "concat(DATE_FORMAT(TIMESTAMP,'%m-%d-%Y %h:'),floor(date_format (TIMESTAMP,'%i') / 15) * 15) ;";
    private static final String queryFor30Minutes = "\n" +
            "select \n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE desc),',',1) as highest,\n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE asc),',',1) as lowest,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP desc),',',1) as close,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP asc),',',1) as open,\n" +
            "concat(DATE_FORMAT(TIMESTAMP,'%m-%d-%Y %h:'),floor(date_format (TIMESTAMP,'%i') / 30) * 30) as TIME\n" +
            "from FINAL_PROJ_DIGIT_CURRENCY_MARKETS where MARKET_PLACE_NAME = :market and CURRENCY_NAME = :currency\n" +
            "group by \n" +
            "concat(DATE_FORMAT(TIMESTAMP,'%m-%d-%Y %h:'),floor(date_format (TIMESTAMP,'%i') / 30) * 30) ;";
    private static final String queryFor1Hour = "select \n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE desc),',',1) as highest,\n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE asc),',',1) as lowest,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP desc),',',1) as close,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP asc),',',1) as open,\n" +
            "DATE_FORMAT(TIMESTAMP,'%m-%d-%Y %h:00') as TIME\n" +
            "from FINAL_PROJ_DIGIT_CURRENCY_MARKETS where MARKET_PLACE_NAME = :market and CURRENCY_NAME = :currency\n" +
            "group by DATE_FORMAT(TIMESTAMP,'%m-%d-%Y %h:00');";
    private static final String queryFor4Hour = "select \n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE desc),',',1) as highest,\n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE asc),',',1) as lowest,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP desc),',',1) as close,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP asc),',',1) as open,\n" +
            "concat(DATE_FORMAT(TIMESTAMP,'%m-%d-%Y '),floor(date_format (TIMESTAMP,'%H') / 4) * 4) as TIME\n" +
            "from FINAL_PROJ_DIGIT_CURRENCY_MARKETS where MARKET_PLACE_NAME = :market and CURRENCY_NAME = :currency\n" +
            "group by concat(DATE_FORMAT(TIMESTAMP,'%m-%d-%Y '),floor(date_format (TIMESTAMP,'%H') / 4) *4) ;";
    private static final String queryForDay = "select \n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE desc),',',1) as highest,\n" +
            "substring_index(group_concat( CURRENCY_PRICE order by CURRENCY_PRICE asc),',',1) as lowest,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP desc),',',1) as close,\n" +
            "substring_index(group_concat(CURRENCY_PRICE order by TIMESTAMP asc),',',1) as open,\n" +
            "DATE_FORMAT(TIMESTAMP,'%m-%d-%Y ') as TIME\n" +
            "from FINAL_PROJ_DIGIT_CURRENCY_MARKETS where MARKET_PLACE_NAME = :market and CURRENCY_NAME = :currency\n" +
            "group by DATE_FORMAT(TIMESTAMP,'%m-%d-%Y ')";
    public static final String PERIOD_15MIN = "15MIN";
    public static final String PERIOD_30MIN = "130MIN";
    public static final String PERIOD_1HOUR = "1HOUR";
    public static final String PERIOD_4HOUR = "4HOUR";
    public static final String PERIOD_DAY = "DAY";
    public static final String generateSqlForPrice (String period){
        switch(period){
            case PERIOD_15MIN:
                return queryFor15Minutes;
            case PERIOD_30MIN:
                return queryFor30Minutes;
            case PERIOD_1HOUR:
                return queryFor1Hour;
            case PERIOD_4HOUR:
                return queryFor4Hour;
            case PERIOD_DAY:
                return queryForDay;
            default:
                return queryFor4Hour;
        }
    }
}
