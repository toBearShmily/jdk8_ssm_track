package com.shmily.controller;

import com.shmily.common.Response;
import com.shmily.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by wuxubiao on 2017/4/28.
 */
@Controller
@RequestMapping("paramBing")
@SessionAttributes(value = "sessionaccountmodel")
public class ParamBingController {


    @ModelAttribute("sessionaccountmodel")
    public User initAccountModel(){
        return new User();
    }

    @RequestMapping(value="/usernamebind", method = {RequestMethod.GET})
    public String userNameBind(Model model, User accountModel){

        model.addAttribute("sessionaccountmodel", new User());
        return "usernamebind";
    }

    @RequestMapping(value="/usernamebind", method = {RequestMethod.POST})
    public String userNameBindPost( @ModelAttribute("sessionaccountmodel") User accountModel){

        //重定向到密码绑定测试
        return "redirect:passwordbind";
    }

    @RequestMapping(value="/passwordbind", method = {RequestMethod.GET})
    public String passwordBind(@ModelAttribute("sessionaccountmodel") User accountModel){

        return "passwordbind";
    }

    @RequestMapping(value="/passwordbind", method = {RequestMethod.POST})
    public String passwordBindPost(@ModelAttribute("sessionaccountmodel") User accountModel, SessionStatus status){

        //销毁@SessionAttributes存储的对象
        status.setComplete();
        //显示绑定结果
        return "sessionmodelbindresult";
    }
}
