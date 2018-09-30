package li.yifei4.util;

import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;

public class EntityManagerUtil {
    public static EntityManager getEntityManager(){
        return ((EntityManagerHolder)
                ApplicationContextHolder
                        .getApplicationContext().getBean("entityManagerHolder")).getEntityManager();
    }
}
