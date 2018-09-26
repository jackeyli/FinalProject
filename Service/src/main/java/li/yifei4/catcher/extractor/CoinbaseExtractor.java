package li.yifei4.catcher.extractor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import li.yifei4.catcher.JsonCatcher;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import net.bytebuddy.matcher.StringMatcher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoinbaseExtractor implements JsonExtractor {

    private List<AbstractMap.SimpleEntry> getAllCurrLists() {
        List<AbstractMap.SimpleEntry> lst = new ArrayList();
        lst.add(new AbstractMap.SimpleEntry("BTC","USD"));
        lst.add(new AbstractMap.SimpleEntry("ETH","USD"));
        lst.add(new AbstractMap.SimpleEntry("BCH","USD"));
        lst.add(new AbstractMap.SimpleEntry("LTC","USD"));
        lst.add(new AbstractMap.SimpleEntry("ETC","USD"));
        return lst;
    }
    private DigitalMarketCurrency getCurrencyMarket(String digCurrency,String currency) throws ParseException {
       JsonCatcher catcher = new JsonCatcher();
        JsonArray jArr = catcher
                .catchFromURL("https://api.pro.coinbase.com/products/" + digCurrency + "-" + currency + "/trades")
                .getAsJsonArray();
       JsonObject jObj = jArr.get(0).getAsJsonObject();
       DigitalMarketCurrency dCurrencyM = new DigitalMarketCurrency();
       dCurrencyM.setName(digCurrency);
       dCurrencyM.setCurrency(currency);
       Pattern p = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2})\\:(\\d{2})\\:(\\d{2})\\.\\d{1,3}Z$");
       Matcher m = p.matcher(jObj.get("time").getAsString());
       m.find();
       SimpleDateFormat spf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
       dCurrencyM.setTimeStamp(m.group(1) + "-" + m.group(2) + "-" + m.group(3) + " " + m.group(4) + ":" + m.group(5) + ":" + m.group(6));
       dCurrencyM.setPrice(jObj.get("price").getAsDouble());
       dCurrencyM.setMarketPlcName("COINBASE");
       return dCurrencyM;
    }
    @Override
    public List<DigitalMarketCurrency> getCurrencyMarket() {
        List<DigitalMarketCurrency> digitalCurrLst = new ArrayList<DigitalMarketCurrency>();
        try {
            for(AbstractMap.SimpleEntry entry : getAllCurrLists()) {
                digitalCurrLst.add(this.getCurrencyMarket(entry.getKey().toString(),entry.getValue().toString()));
            }
            return digitalCurrLst;
        }catch(Exception e) {
            RuntimeException rte = new RuntimeException();
            rte.setStackTrace(e.getStackTrace());
            throw rte;
        }
    }
}
