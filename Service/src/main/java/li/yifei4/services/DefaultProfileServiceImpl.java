package li.yifei4.services;

import li.yifei4.beans.UserBean;
import li.yifei4.datas.dao.UserDao;
import li.yifei4.datas.entity.User;
import li.yifei4.util.EntityManagerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("profileService")
public class DefaultProfileServiceImpl implements ProfileService{
    @Resource(name="userEntityDao")
    private UserDao userDao;

    @Override
    public boolean addUser(UserBean user) {
        if(userDao.findByName(user.getName()).size() == 0){
            User userEntity = new User();
            userEntity.setEmail(user.getEmail());
            userEntity.setName(user.getName());
            userEntity.setPhone(user.getPhone());
            userEntity.setPassword(user.getPassword());
            EntityManagerUtil.getEntityManager().persist(userEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(UserBean user) {
        List<User> users = userDao.findByNameNPassword(user.getName(),user.getPassword());
        if(users.size() == 0)
            return null;
        return users.get(0);
    }
}
