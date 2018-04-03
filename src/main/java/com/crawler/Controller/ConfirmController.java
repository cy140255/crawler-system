package com.crawler.Controller;

import com.crawler.Service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.QueryParam;

/**
 * Created by 14025 on 2017/9/27.
 */

@Controller
@RequestMapping("/confirm/*")
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @RequestMapping(value = "/idConfirm.do", method = RequestMethod.POST)
    @ResponseBody

    public String idConfirm(@QueryParam("id") String id) {

        return confirmService.idConfirm(id);
    }
}
