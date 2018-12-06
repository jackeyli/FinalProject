package li.yifei4.datas.dao;

import li.yifei4.datas.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userEntityDao")
public class UserDao extends EntityDao<User> {
    public List findByName(String name) {
        MyCriteriaBuilder<User> builder = this.getCriteriaBuilder();
        builder.where(builder.predicate("equal", builder.get("name"), Optional.ofNullable(name).orElse("")));
        return builder.createExecutiveQuery().getResultList();
    }
    public List findByNameNPassword(String name,String password) {
        MyCriteriaBuilder<User> builder = this.getCriteriaBuilder();
        builder.where(
                builder.and(builder.predicate("equal",builder.get("password"),
                        Optional.ofNullable(password).orElse("")),
                builder.predicate("equal", builder.get("name"),
                        Optional.ofNullable(name).orElse(""))));
        return builder.createExecutiveQuery().getResultList();
    }
}
