package com.crawler.Controller;

import com.crawler.Service.FileLoadService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by 14025 on 2017/8/29.
 */
@Controller
@RequestMapping("/login/*")
public class LoginController {
    @Autowired
    private FileLoadService fileLoadService;

    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public void login(@QueryParam("url")String url, HttpServletResponse response,HttpServletRequest request){
       fileLoadService.analysisUrl(url);
       String htmlName = fileLoadService.getHtmlName(url);
        File file =new File("D:/"+htmlName);
        response.setCharacterEncoding("UTF-8");
        try {
            response.addHeader("Content-Disposition","attachment; filename=" + new String(htmlName.getBytes("UTF-8"),"ISO-8859-1")+".zip");
            response.setContentType("mutipart/form-data");
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

            String[] fileNames= file.list();
            byte[] bytes = new byte[1024];
            int len = 0;
            for (String filName:fileNames){
                FileInputStream fileInputStream = new FileInputStream(new File("D:/"+htmlName+"/"+filName));
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

}
