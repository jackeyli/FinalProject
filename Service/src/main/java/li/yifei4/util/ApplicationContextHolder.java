package li.yifei4.util;

import org.springframework.context.ApplicationContext;


public class ApplicationContextHolder {
    private static ApplicationContext applicationContext;
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    public static void setApplicationContext(ApplicationContext ctx){
        applicationContext = ctx;
    }
}
