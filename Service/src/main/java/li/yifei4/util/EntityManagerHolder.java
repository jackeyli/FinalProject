package li.yifei4.util;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component("entityManagerHolder")
public class EntityManagerHolder{
    @PersistenceContext
    EntityManager em;
    public  EntityManager getEntityManager(){
       // em.joinTransaction();
        return em;
    }
}