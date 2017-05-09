package com.shmily.util.weiXin;

import com.shmily.support.weixin.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
public class MessageUtil {
    private static Logger log = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * xml转为map
     * @param request
     * @return
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws DocumentException {
        Map<String , String> map = new HashMap<>();

        SAXReader reader = new SAXReader();

        InputStream ins = null;
        try {
            //从请求中获取输入的信息
            ins = request.getInputStream();
        } catch (IOException e) {
            log.error("获取请求数据失败！！！");
        } catch (NullPointerException e){
            log.error("请求体为null!!!");
        }

        Document doc = reader.read(ins);

        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for (Element element : list) {
            map.put(element.getName(), element.getText());
        }

        return map;
    }


    /**
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();

        //将xml的根据点替换为<xml>
        xStream.alias("xml",textMessage.getClass());

        return xStream.toXML(textMessage);
    }

    public static TextMessage messageResult(String FromUserName, String ToUserName, String context){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(FromUserName);
        textMessage.setFromUserName(ToUserName);
        textMessage.setCreateTime(DateTime.now().getMillis());
        textMessage.setMsgType("text");
        return textMessage;
    }
}
