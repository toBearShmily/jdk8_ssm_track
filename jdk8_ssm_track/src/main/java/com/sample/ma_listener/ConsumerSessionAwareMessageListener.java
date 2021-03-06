package com.sample.ma_listener;

import com.sample.activeMq.Producer;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 * SessionAwareMessageListener是Spring为我们提供的，它不是标准的JMS MessageListener。
 * MessageListener的设计只是纯粹用来接收消息的，假如我们在使用MessageListener处理接收到的消息时
 * 我们需要发送一个消息通知对方我们已经收到这个消息了，那么这个时候我们就需要在代码里面去
 * 重新获取一个Connection或Session。SessionAwareMessageListener的设计就是为了方便
 * 我们在接收到消息后发送一个回复的消息，它同样为我们提供了一个处理接收到的消息的onMessage方法，
 * 但是这个方法可以同时接收两个参数，一个是表示当前接收到的消息Message，
 * 另一个就是可以用来发送消息的Session对象
 * Created by Administrator on 2017/2/15.
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {

    private Destination destination;

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        System.out.println("ConsumerSessionAwareMessageListener收到一条消息！！！");
        System.out.println("ConsumerSessionAwareMessageListener收到的消息内容为："+textMessage.getText());
        MessageProducer messageProducer = session.createProducer(destination);
        Message message = session.createTextMessage("收到消息后通知当前生产者，ConsumerSessionAwareMessageListener我收到了");
        messageProducer.send(message);

    }
}
