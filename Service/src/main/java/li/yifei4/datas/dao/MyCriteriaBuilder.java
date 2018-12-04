package li.yifei4.datas.dao;


import li.yifei4.util.EntityManagerUtil;

import java.util.Arrays;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private boolean isExecutingMethod(Method m ,String methodName,int parameterCount,Class<?> ... paramClasses) {
        boolean isValid = Objects.equals(m.getName(),methodName) &&
                m.getParameterCount() == parameterCount;
        if(!isValid)
            return false;
        List<Class<?>> params = Arrays.asList(m.getParameterTypes());
        List<Class<?>> inputParam = Arrays.asList(paramClasses);
        for(int i = 0; i < inputParam.size(); i ++)
        {
            Class<?> mClass = params.get(i);
            isValid = isValid && mClass.isAssignableFrom(inputParam.get(i));
        }
        return isValid;
    }
    private Method getExecuteMethod(String methodName,Class<?> ...paramClasses){
        List<Method> methods = Arrays.asList(cb.getClass().getDeclaredMethods()).stream()
                .filter(m->isExecutingMethod(m,methodName,paramClasses.length,paramClasses)).collect(Collectors.toList());
        if(methods.size() == 0)
            throw new RuntimeException("No Such Method");
        return methods.get(0);
    }
    public Predicate predicate(String methodName,Object ...params){
        try {
            Class<?>[] clazzes = new Class<?>[params.length];
            for(int i = 0; i < params.length; i ++)
            {
                clazzes[i] = params[i].getClass();
            }
            Method method = getExecuteMethod(methodName,clazzes);
            return (Predicate) method.invoke(cb,params);
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
        return EntityManagerUtil.getEntityManager().createQuery(query);
    }
}
