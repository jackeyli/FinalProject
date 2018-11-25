package li.yifei4.catcher.extractor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import li.yifei4.catcher.JsonCatcher;
import li.yifei4.datas.entity.DigitalMarketCurrency;

import java.text.SimpleDateFormat;
import java.util.*;

public class HUOBIExtractor implements JsonExtractor{
    @Override
    public List<DigitalMarketCurrency> getCurrencyMarket() {
        JsonArray jsonData = new JsonCatcher().catchFromURL("https://www.huobi.com/-/x/general/index/constituent_symbol/detail?r=r4lp0slvvia")
                .getAsJsonObject().getAsJsonObject("data").getAsJsonArray("symbols");
        List<DigitalMarketCurrency> digitalCurrLst = new ArrayList<DigitalMarketCurrency>();
        try {
            for(AbstractMap.SimpleEntry entry : getAllCurrLists()) {
                for(JsonElement ele : jsonData){
                    if(Objects.equals(ele.getAsJsonObject().get("symbol").getAsString(),
                            (entry.getKey().toString() + entry.getValue().toString()).toLowerCase())){
                        DigitalMarketCurrency dCurrencyM = new DigitalMarketCurrency();
                        dCurrencyM.setName(entry.getKey().toString());
                        dCurrencyM.setCurrency(entry.getValue().toString());
                        dCurrencyM.setMarketPlcName("HUOBI");
                        dCurrencyM.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                        dCurrencyM.setPrice(ele.getAsJsonObject().get("close").getAsDouble());
                        digitalCurrLst.add(dCurrencyM);
                        break;
                    }
                }
            }
            return digitalCurrLst;
        }catch(Exception e) {
            RuntimeException rte = new RuntimeException();
            rte.setStackTrace(e.getStackTrace());
            throw rte;
        }
    }
}
