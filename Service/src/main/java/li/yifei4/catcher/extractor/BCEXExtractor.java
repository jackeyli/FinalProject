package li.yifei4.catcher.extractor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import li.yifei4.catcher.JsonCatcher;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import java.text.SimpleDateFormat;
import java.util.*;

public class BCEXExtractor implements JsonExtractor{
    @Override
    public List<DigitalMarketCurrency> getCurrencyMarket() {
        JsonArray jsonData = new JsonCatcher().catchFromURL("https://www.bcex.top/index/PreloadingCoins")
                .getAsJsonObject().getAsJsonObject("data").getAsJsonObject("coin_list")
                .getAsJsonArray("CK.USD");
        List<DigitalMarketCurrency> digitalCurrLst = new ArrayList<DigitalMarketCurrency>();
        for(AbstractMap.SimpleEntry entry : getAllCurrLists()) {
            DigitalMarketCurrency dCurrencyM = new DigitalMarketCurrency();
            dCurrencyM.setName(entry.getKey().toString());
            dCurrencyM.setCurrency(entry.getValue().toString());
            dCurrencyM.setMarketPlcName("BCEX");
            dCurrencyM.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            double price = -1;
            for(int i = 0; i < jsonData.size(); i ++){
                JsonObject thisObject = jsonData.get(i)
                        .getAsJsonObject();
                if(Objects.equals(thisObject
                        .get("coin_from").getAsString()
                        .toUpperCase(),entry.getKey())){
                    price = Double.valueOf(thisObject.get("price").getAsString());
                    dCurrencyM.setPrice(price);
                    break;
                }
            }
            if(price > 0){
                digitalCurrLst.add(dCurrencyM);
            } else {
                dCurrencyM.setPrice(-1);
                digitalCurrLst.add(dCurrencyM);
            }
        }
        return digitalCurrLst;
    }
}
