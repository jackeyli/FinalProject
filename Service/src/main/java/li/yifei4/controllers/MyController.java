package li.yifei4.controllers;
import li.yifei4.services.CurrencyService;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

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
        myService.storeCurrencyMarket(null);
        return "success";
    }
    @RequestMapping("/")
    @ResponseBody
    public String getCurrencies() throws IOException, ClientProtocolException {
        myService.getCurrencyMarkets("COINBASE");
        return "success";
    }
}
