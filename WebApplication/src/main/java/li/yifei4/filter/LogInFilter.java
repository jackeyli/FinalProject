package li.yifei4.filter;
import li.yifei4.beans.UserBean;
import li.yifei4.datas.entity.User;
import li.yifei4.services.ProfileService;
import li.yifei4.util.ApplicationContextHolder;
import li.yifei4.util.UserAgent;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class LogInFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        if(request.getRequestURL().indexOf("/login") >= 0){
            // 放过
            filterChain.doFilter(servletRequest,servletResponse);
        } else if(request.getRequestURL().indexOf("/signUp") >= 0){
            // 放过
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            //block
            ProfileService pfService = (ProfileService)
                    ApplicationContextHolder.getApplicationContext().getBean("profileService");
            Cookie[] cookies = request.getCookies();
            String userName = null;
            String password = null;
            for(Cookie cookie : cookies){
                if(Objects.equals(cookie.getName(),"Credential")){
                    String[] str = cookie.getValue().split("\\|");
                    if(str.length >= 2) {
                        userName = str[0];
                        password = str[1];
                    }
                    break;
                }
            }
            UserBean ubean = new UserBean();
            ubean.setName(userName);
            ubean.setPassword(password);
            User user = pfService.getUser(ubean);
            if(user != null){
                UserAgent.INSTANCE.getInstance().setUserID(user.getOid());
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                servletResponse.getWriter().print("Please log in");
            }
        }

    }
    @Override
    public void destroy() {

    }
}
