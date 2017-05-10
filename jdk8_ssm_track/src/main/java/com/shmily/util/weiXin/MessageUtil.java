package com.shmily.util.weiXin;

import com.shmily.support.weixin.WeiXin;
import com.shmily.support.weixin.entity.Image;
import com.shmily.support.weixin.entity.ImageMessage;
import com.shmily.support.weixin.entity.TextMessage;
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
import java.util.ArrayList;
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
     * @return
     */
    public static String textMessageToXml(Object object){
        XStream xStream = new XStream();

        //将xml的根据点替换为<xml>
        if(object instanceof TextMessage){
            xStream.alias("xml",TextMessage.class);
            return xStream.toXML((TextMessage)object);
        }else if(object instanceof ImageMessage){
            xStream.alias("xml",ImageMessage.class);
            xStream.alias("item",Image.class);
            return xStream.toXML((ImageMessage)object);
        }
        return "";
    }

    /**
     * 回复文本消息
     * @param FromUserName
     * @param ToUserName
     * @param
     * @return
     */
    public static TextMessage textMessageResult(String FromUserName, String ToUserName){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(FromUserName);
        textMessage.setFromUserName(ToUserName);
        textMessage.setCreateTime(DateTime.now().getMillis());
        textMessage.setMsgType(WeiXin.MESSAGE_TEXT);
        return textMessage;
    }

    /**
     * 回复图文消息
     * @param FromUserName
     * @param ToUserName
     * @return
     */
    public static ImageMessage imageMessageResult(String FromUserName, String ToUserName){
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName(FromUserName);
        imageMessage.setFromUserName(ToUserName);
        imageMessage.setCreateTime(DateTime.now().getMillis());
        imageMessage.setMsgType(WeiXin.MESSAGE_NEWS);

        Image image = new Image();
        image.setTitle("看这里");
        image.setDescription("我好看吗？");
        image.setPicUrl("http://wxb.tunnel.qydev.com/ssm/resources/img/a1.jpg");
        image.setUrl("http://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/00137402760310626208b4f695940a49e5348b689d095fc000");

        List<Image> list = new ArrayList<>();
        list.add(image);
        imageMessage.setArticleCount(list.size());
        imageMessage.setArticles(list);
        return imageMessage;
    }
}
