package com.crawler.Controller;

import com.crawler.Entity.UserInfo;
import com.crawler.Service.FileLoadService;
import com.crawler.Service.UserService;
import com.util.DateTimeUtil;
import com.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.util.Objects.nonNull;
/**
 * Created by 14025 on 2017/8/29.
 */

@Controller
public class LoginController {
    @Autowired
    private FileLoadService fileLoadService;

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public void login(@QueryParam("url")String url, HttpServletResponse response,HttpServletRequest request){
        String filePath = request.getSession().getServletContext().getRealPath("");
       fileLoadService.analysisUrl(url,filePath);

        String htmlName = fileLoadService.getHtmlName(url);
        File file =new File(filePath+htmlName);
        response.setCharacterEncoding("UTF-8");
        try {
            response.addHeader("Content-Disposition","attachment; filename=" + new String(htmlName.getBytes("UTF-8"),"ISO-8859-1")+".zip");
            response.setContentType("mutipart/form-data");
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
            String[] fileNames= file.list();
            byte[] bytes = new byte[1024];
            int len = 0;
            for (String filName:fileNames){
                FileInputStream fileInputStream = new FileInputStream(new File(filePath+htmlName+"/"+filName));
                zipOutputStream.putNextEntry(new ZipEntry(filName));
                while ((len = fileInputStream.read(bytes))!=-1){
                    zipOutputStream.write(bytes,0,len);
                }

                zipOutputStream.closeEntry();
                fileInputStream.close();
            }

            zipOutputStream.close();
        }catch (Exception e){
            e.getMessage();
        }

        File[] files = file.listFiles();
        for(int i=0;i<files.length;i++){
            File f= new File(files[i].getAbsolutePath());
            f.delete();
        }
        file.delete();

    }

//    @RequestMapping(value = "/menu.do",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> getMenu(){
//
//    }




    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public String register(@QueryParam("userName")String userName, @QueryParam("password")String password,HttpServletRequest request){
              HttpSession session = request.getSession();
        userService.register(userName,password);

        //伪装成有好友，方便前端统一写法，传给前端的是一个json数组，而不是单个json，单个json表示没有好友
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
       List<UserInfo> users = new ArrayList<>();
       users.add(userInfo);
       //这里结束
        try{
            session.setAttribute("users",JsonUtil.JsonUtil(users)); //将 users的 json格式存在session中
            return JsonUtil.JsonUtil(users);
        }catch (Exception e){

        }
       return null;

    }



@RequestMapping(value = "loginIn.do",method = RequestMethod.POST)
@ResponseBody
public String loginIn(@QueryParam("userName")String userName, @QueryParam("password")String password,HttpSession session){

        try{
            UserInfo userInfo = userService.loginIn(userName,password);
            if (nonNull(userInfo)) {
                List<UserInfo> userInfos = userService.getFriend(userInfo.getId());
                userInfos.add(userInfo);
                session.setAttribute("users",JsonUtil.JsonUtil(userInfos));
                return JsonUtil.JsonUtil(userInfos);

            }
        }catch (Exception e){

        }
        return "error";
}



    @RequestMapping(value = "/getUserName.do",method = RequestMethod.GET)
    @ResponseBody
    public String ajax(HttpServletRequest request){
        HttpSession session = request.getSession();

        return (String)session.getAttribute("users"); //然后这里取值 根据key值取
    }

    @RequestMapping(value = "/exit.do",method = RequestMethod.GET)
    @ResponseBody
    public String exit(HttpSession session){
        session.setAttribute("users",null);
        return "success";
    }



    @RequestMapping(value = "/checkUserName.do",method = RequestMethod.GET)
    @ResponseBody
    public String checkUserName(@QueryParam("username")String username){
       Boolean flag = userService.checkUserName(username);
        if (flag == true) {
            return "success";
        }
        return "error";
    }



    @RequestMapping(value = "/addFriend.do",method = RequestMethod.GET)
    @ResponseBody
    public String addFriend(@QueryParam("username")String username){
        Boolean flag = userService.checkUserName(username);
        if (flag == true){
            return "false";
        }else {
            return "true";
        }

    }
}
