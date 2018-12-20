package com.core.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {
    protected Object CurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("true", "false", true));
//        binder.registerCustomEditor(Double.class, new DoubleEditor());
//        binder.registerCustomEditor(Integer.class, new IntegerEditor());
    }

}
