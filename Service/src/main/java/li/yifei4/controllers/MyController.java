package li.yifei4.controllers;
import li.yifei4.beans.CurrencyPriceQueryBean;
import li.yifei4.beans.NotificationBean;
import li.yifei4.beans.ResponseDTO;
import li.yifei4.datas.entity.DigitalMarketCurrency;
import li.yifei4.datas.entity.NotificationCondition;
import li.yifei4.services.ConditionService;
import li.yifei4.services.CurrencyService;
import li.yifei4.util.UserAgent;
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

    @Resource(name="conditionService")
    private ConditionService conditionService;

    @RequestMapping("/myProcess")
    @ResponseBody
    public ResponseDTO myProcess() throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try {
            myService.storeCurrencyMarket();
            resp.setContent("success");
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
    @RequestMapping("/getCurrencies")
    @ResponseBody
    public ResponseDTO getCurrencies() throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try {
             resp.setContent(myService.getCurrencyMarkets());
        }catch (Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
    @RequestMapping("/currencyPriceHistory")
    @ResponseBody
    public ResponseDTO getCurrencyPriceHistory(@RequestBody CurrencyPriceQueryBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            resp.setContent(myService.currencyPriceHistory(bean));
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally{
            return resp;
        }
    }

    @RequestMapping("/storeNotiCondition")
    @ResponseBody
    public ResponseDTO  storeNotiCondition(@RequestBody NotificationBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        bean.setUserOid(UserAgent.INSTANCE.getInstance().getUserID());
        try{
            resp.setContent(conditionService.storeNotification(bean));
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }

    @RequestMapping("/notiCondition")
    @ResponseBody
    public ResponseDTO  getNotiCondition() throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        int userId = UserAgent.INSTANCE.getInstance().getUserID();
        try{
            resp.setContent(conditionService.getNotificationCondition(userId));
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }

    @RequestMapping("/delNotiCondition")
    @ResponseBody
    public ResponseDTO  deleteNotiCondition(@RequestParam int id) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            conditionService.removeNotificationCondition(id);
            resp.setContent("success");
        }catch(Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
}
