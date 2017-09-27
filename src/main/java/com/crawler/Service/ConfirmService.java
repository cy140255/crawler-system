package com.crawler.Service;

import org.springframework.stereotype.Service;

/**
 * Created by 14025 on 2017/9/27.
 */
@Service
public class ConfirmService {

    public String idConfirm(String id){
        if (id.equals("nbsp")){
            return "success";
        }else {
            return "error";
        }
    }


}
