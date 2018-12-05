package li.yifei4.catcher.extractor;

import com.google.gson.JsonObject;
import li.yifei4.catcher.JsonCatcher;
import li.yifei4.datas.entity.DigitalMarketCurrency;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class POLONIEXExtractor implements JsonExtractor {
    @Override
    public List<DigitalMarketCurrency> getCurrencyMarket() {
        JsonObject jsonData = new JsonCatcher().catchFromURL("https://poloniex.com/public?command=returnTicker")
                .getAsJsonObject();
        List<DigitalMarketCurrency> digitalCurrLst = new ArrayList<DigitalMarketCurrency>();
        try {
            for(AbstractMap.SimpleEntry entry : getAllCurrLists()) {
                DigitalMarketCurrency dCurrencyM = new DigitalMarketCurrency();
                dCurrencyM.setName(entry.getKey().toString());
                dCurrencyM.setCurrency(entry.getValue().toString());
                dCurrencyM.setMarketPlcName("POLONIEX");
                dCurrencyM.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                String key = Objects.equals(entry.getKey().toString(),"BCH") ? "BCHABC": entry.getKey().toString();
                String value_1 = "USDT";
                dCurrencyM.setPrice(
                        new BigDecimal(Optional
                                .ofNullable(jsonData.getAsJsonObject(entry.getValue().toString() + "_" + key))
                                .orElse(jsonData.getAsJsonObject("USDC_" + key))
                                .get("last").getAsString())
                                .doubleValue());
                digitalCurrLst.add(dCurrencyM);
            }
            return digitalCurrLst;
        }catch(Exception e) {
            RuntimeException rte = new RuntimeException();
            rte.setStackTrace(e.getStackTrace());
            throw rte;
        }
    }
}
