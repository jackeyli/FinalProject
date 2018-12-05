package li.yifei4.catcher.extractor;

import com.google.gson.JsonObject;
import li.yifei4.catcher.JsonCatcher;
import li.yifei4.datas.entity.DigitalMarketCurrency;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class COINEXExtractor implements JsonExtractor {
    @Override
    public List<DigitalMarketCurrency> getCurrencyMarket() {
        JsonObject jsonData = new JsonCatcher().catchFromURL("https://www.coinex.com/res/market/price")
                .getAsJsonObject().getAsJsonObject("data");
        List<DigitalMarketCurrency> digitalCurrLst = new ArrayList<DigitalMarketCurrency>();
        try {
            for(AbstractMap.SimpleEntry entry : getAllCurrLists()) {
                DigitalMarketCurrency dCurrencyM = new DigitalMarketCurrency();
                dCurrencyM.setName(entry.getKey().toString());
                dCurrencyM.setCurrency(entry.getValue().toString());
                dCurrencyM.setMarketPlcName("COINEX");
                dCurrencyM.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                dCurrencyM.setPrice(
                        new BigDecimal(jsonData.getAsJsonObject(dCurrencyM.getName()).get(entry.getValue().toString()).getAsString())
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
