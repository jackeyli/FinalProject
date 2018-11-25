package li.yifei4.job;

import li.yifei4.services.CurrencyService;
import li.yifei4.util.ApplicationContextHolder;
import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.Arrays;

@DisallowConcurrentExecution
public class ExtractionJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Logger.getLogger("actuallyUsingLog").info("Job Executed!");
        ((CurrencyService)ApplicationContextHolder
                .getApplicationContext().getBean("currencyStockService")).storeCurrencyMarket(Arrays.asList(new String[]{"COINBASE","COINEX","HUOBI","POLONIEX"}));
    }
}
