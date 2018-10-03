package li.yifei4.launcher;

import li.yifei4.job.ExtractionJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class LauncherServlet extends HttpServlet {
    private Scheduler scheduler = null;
    public void init() throws ServletException {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("extractor")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ? *")).build();
            scheduler.scheduleJob(JobBuilder.newJob(ExtractionJob.class).build(),trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            scheduler.clear();
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}