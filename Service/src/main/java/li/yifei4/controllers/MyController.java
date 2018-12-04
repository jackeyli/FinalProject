package li.yifei4.controllers;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import li.yifei4.services.CurrencyService;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
@Controller
@RequestMapping("/test")
public class MyController {
    @Resource(name="currencyStockService")
    private CurrencyService myService;
    @RequestMapping("/myProcess")
    @ResponseBody
    public String myProcess() throws IOException, ClientProtocolException {
        myService.storeCurrencyMarket();
        return "success";
    }
    @RequestMapping("/getCurrencies")
    @ResponseBody
    public List<DigitalMarketCurrency> getCurrencies() throws IOException, ClientProtocolException {
        return myService.getCurrencyMarkets();
    }
}
