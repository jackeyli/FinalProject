package li.yifei4.datas;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class EntityManagerUtils {
    private static class MyEntityManager{
        private static EntityManager instance = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();
    }
    public static EntityManager getEntityManager(){
        return Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();
    }
}