package li.yifei4.datas.dao;

import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
@Component
public abstract class EntityDao<T> {
    private final Class<T> classType;
    public EntityDao(){
        this.classType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public MyCriteriaBuilder getCriteriaBuilder(){
        MyCriteriaBuilder builder = new MyCriteriaBuilder();
        CriteriaBuilder cb = EntityManagerUtil.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(classType);
        Root<T> root = query.from(classType);
        builder.setCb(cb);
        builder.setQuery(query.select(root));
        builder.setRoot(root);
        return builder;
    }
    public void persist(T t){
        EntityManagerUtil.getEntityManager().persist(t);
        //EntityManagerUtil.getEntityManager().flush();
    }
    public List<T> findAll(){
        MyCriteriaBuilder<T> builder = this.getCriteriaBuilder();
        return builder.createExecutiveQuery().getResultList();
    }
}
