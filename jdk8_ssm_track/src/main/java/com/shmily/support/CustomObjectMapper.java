package com.shmily.support;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 扩展Jackson提供的ObjectMapper类
 * 需要对Jackson的序列化行为进行定制，比如，排除值为空属性、进行缩进输出、将驼峰转为下划线、进行日期格式化等
 * wxb：将需要返回的json数据在此进行操作，返回定制数据
 * Created by Administrator on 2017/1/6.
 */
public class CustomObjectMapper extends ObjectMapper{
    /*时间格式处理*/
    private String dateFormatPattern;

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public void init(){
        // 排除值为空属性
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 进行缩进输出
        configure(SerializationFeature.INDENT_OUTPUT, true);
        // 进行日期格式化
        if (StringUtils.isNotEmpty(dateFormatPattern)) {
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            setDateFormat(dateFormat);
        }
    }
}
