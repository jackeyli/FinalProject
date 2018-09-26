package li.yifei4.datas.dao;

import li.yifei4.datas.EntityManagerUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyCriteriaBuilder<T>{
    private CriteriaBuilder cb;
    private Root<T> root;
    private CriteriaQuery<T> query;
    private Predicate predicate;
    public Expression get(String name){
        return root.get(name);
    }
    public Order desc (Expression exp){
        return cb.desc(exp);
    }
    public Order asc(Expression exp) {
        return cb.asc(exp);
    }
    public Predicate and(Predicate ... preds) {
        return cb.and(preds);
    }
    public Predicate predicate(String methodName, Object param){
        try {
            Method method = cb.getClass().getDeclaredMethod(methodName, param.getClass());
            return (Predicate) method.invoke(cb, param);
        }catch(Exception e) {
            RuntimeException re = new RuntimeException();
            re.setStackTrace(e.getStackTrace());
            throw re;
        }
    }
    public Predicate predicate(String methodName,Object param1,Object param2){
        try {
            Method method = cb.getClass().getDeclaredMethod(methodName, param1.getClass(), param2.getClass());
            return (Predicate) method.invoke(cb, param1, param2);
        }catch(Exception e) {
            RuntimeException re = new RuntimeException();
            re.setStackTrace(e.getStackTrace());
            throw re;
        }
    }
    public Predicate predicate(String methodName,Object param1,Object param2,Object param3){
        try {
            Method method = cb.getClass().getDeclaredMethod(methodName, param1.getClass(), param2.getClass(), param3.getClass());
            return (Predicate) method.invoke(cb, param1, param2, param3);
        }catch(Exception e) {
            RuntimeException re = new RuntimeException();
            re.setStackTrace(e.getStackTrace());
            throw re;
        }
    }
    public void setCb(CriteriaBuilder cb) {
        this.cb = cb;
    }
    public void setRoot(Root root) {
        this.root = root;
    }
    public void setQuery(CriteriaQuery query) {
        this.query = query;
    }
    public CriteriaQuery<T> where (Predicate ... p){
        return query.where(p);
    }
    public TypedQuery<T> createExecutiveQuery(){
        return EntityManagerUtils.getEntityManager().createQuery(query);
    }
}
