package li.yifei4.util;

public enum UserAgent {
    INSTANCE;
    private UserAgent.UserHolder user;
    UserAgent(){
        user = new UserAgent.UserHolder();
    }
    public UserHolder getInstance(){
        return user;
    }
    public class UserHolder {
        private ThreadLocal<Integer> rLocal = new ThreadLocal<Integer>();
        public int getUserID(){
            return rLocal.get();
        }
        public void setUserID(int userID){
            rLocal.set(userID);
        }
    }
}
