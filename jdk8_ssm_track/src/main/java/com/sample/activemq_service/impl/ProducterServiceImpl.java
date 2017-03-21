package com.sample.activemq_service.impl;

import com.sample.activemq_service.ProducterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Administrator on 2017/2/15.
 */
@Component
public class ProducterServiceImpl implements ProducterService{

    @Autowired
    @Qualifier("JmsTemplate")
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, String message) {
        System.out.println("生产者发送消息");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("我是生产者发送的消息:"+message);
            }
        });
    }
}
