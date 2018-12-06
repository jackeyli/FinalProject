package li.yifei4.services;

import li.yifei4.beans.UserBean;
import li.yifei4.datas.entity.User;

public interface ProfileService  {
    boolean addUser(UserBean user);
    User getUser(UserBean user);
}
