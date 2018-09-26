package li.yifei4.datas.dao;

import li.yifei4.datas.EntityManagerUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class EntityDao<T> {
    private EntityManager em = EntityManagerUtils.getEntityManager();
    private CriteriaBuilder cb = em.getCriteriaBuilder();
    private final Class<T> classType;
    public EntityDao(){
        this.classType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public MyCriteriaBuilder getCriteriaBuilder(){
        MyCriteriaBuilder builder = new MyCriteriaBuilder();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(classType);
        Root<T> root = query.from(classType);
        builder.setCb(cb);
        builder.setQuery(query.select(root));
        builder.setRoot(root);
        return builder;
    }
    public void persist(T t){
        em.persist(t);
    }
    public List<T> findAll(){
        MyCriteriaBuilder<T> builder = this.getCriteriaBuilder();
        return builder.createExecutiveQuery().getResultList();
    }
}
