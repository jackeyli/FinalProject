package li.yifei4.listener;

import li.yifei4.util.ApplicationContextHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

public class MyContextListener extends ContextLoaderListener {

    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        ApplicationContextHolder.setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
    }
}
