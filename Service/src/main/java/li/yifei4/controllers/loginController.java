package li.yifei4.controllers;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import jdk.nashorn.internal.runtime.regexp.RegExpMatcher;
import li.yifei4.beans.ResponseDTO;
import li.yifei4.beans.UserBean;
import li.yifei4.datas.entity.User;
import li.yifei4.services.ProfileService;
import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/login")
public class loginController {
    @Resource(name="profileService")
    ProfileService profileService;

    @RequestMapping("/")
    @ResponseBody
    public ResponseDTO loginProcess(@RequestBody UserBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            User user = profileService.getUser(bean);
            if(user == null) {
                resp.setErrorMessage("User Name or password incorrect");
            } else {
                Cookie nCookie = new Cookie("Credential",user.getName() + "|" + user.getPassword());
                nCookie.setPath("/");
                Pattern r =  Pattern.compile("^http[s]?\\:\\/\\/([^:/]*)[:|/].*$");
                Matcher m = r.matcher(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                        .getRequest().getRequestURL());
                if(m.find()){
                    nCookie.setDomain(m.group(1));
                }
                ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse()
                        .addCookie(nCookie);
                resp.setContent("success");
            }
        }catch (Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }

    @RequestMapping("/signUp")
    @ResponseBody
    public ResponseDTO signUpProcess(@RequestBody UserBean bean) throws IOException, ClientProtocolException {
        ResponseDTO resp = new ResponseDTO();
        try{
            if(profileService.addUser(bean)){
                Cookie nCookie = new Cookie("Credential",bean.getName() + "|" + bean.getPassword());
                nCookie.setPath("/");
                Pattern r =  Pattern.compile("^http[s]?\\:\\/\\/([^:/]*)[:|/].*$");
                Matcher m = r.matcher(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                        .getRequest().getRequestURL());
                if(m.find()){
                    nCookie.setDomain(m.group(1));
                }
                ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse()
                        .addCookie(nCookie);
                resp.setContent("success");
            } else {
                resp.setErrorMessage("user existed");
            }
        }catch (Throwable t){
            resp.setErrorMessage(t.getMessage());
        }finally {
            return resp;
        }
    }
}
