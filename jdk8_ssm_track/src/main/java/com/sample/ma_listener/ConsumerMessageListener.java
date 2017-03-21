package com.sample.ma_listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 对用的消费者监听，，当监听到有消息则接收
 * 生产者往指定目的地Destination发送消息后，接下来就是消费者对指定目的地的消息进行消费了。
 * 那么消费者是如何知道有生产者发送消息到指定目的地Destination了呢？
 * 这是通过Spring为我们封装的消息监听容器MessageListenerContainer实现的，
 * 它负责接收信息，并把接收到的信息分发给真正的MessageListener进行处理。
 * 每个消费者对应每个目的地都需要有对应的MessageListenerContainer。
 * 对于消息监听容器而言，除了要知道监听哪个目的地之外，还需要知道到哪里去监听，
 * 也就是说它还需要知道去监听哪个JMS服务器，这是通过在配置MessageConnectionFactory的时候往里面注入
 * 一个ConnectionFactory来实现的。所以我们在配置一个MessageListenerContainer的时候有三个属性必须指定，
 * 一个是表示从哪里监听的ConnectionFactory；一个是表示监听什么的Destination；一个是接收到消息以后
 * 进行消息处理的MessageListener。Spring一共为我们提供了两种类型的MessageListenerContainer，
 * SimpleMessageListenerContainer和DefaultMessageListenerContainer
 *
 * DefaultMessageListenerContainer会动态的适应运行时需要，并且能够参与外部的事务管理。
 * 它很好的平衡了对JMS提供者要求低、先进功能如事务参与和兼容Java EE环境
 *
 * Created by Administrator on 2017/2/15.
 */
public class ConsumerMessageListener implements MessageListener {
    private static Logger log = LoggerFactory.getLogger(ConsumerMessageListener.class);
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        System.out.println("消费者(ConsumerMessageListener)接收到一条文本消息");
        try {
            System.out.println("消费者(ConsumerMessageListener)接收到的消息为："+tm.getText());
            /*if(1==1){
                throw new RuntimeException("出错啦!!!");
            }*/
        } catch (JMSException e) {
            log.error("消费者接收消息异常："+e.getMessage());
        }
    }
}
