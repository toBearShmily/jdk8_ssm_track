package com.shmily.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/1/16.
 */
public class ValidateJson {
    private static Logger logger = LoggerFactory.getLogger(ValidateJson.class);
    public static Map<String,String> getJsonString(BindingResult bindingResult){
        Map<String,String> map = new HashMap<>();
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for(int i = 0 ; i < allErrors.size() ; i++){
                map.put("info"+i,allErrors.get(i).getDefaultMessage());
                logger.info(Constants.REQUEST_MSG,allErrors.get(i).getDefaultMessage());
            }
        }else
            return null;
        return map;
    }
}
