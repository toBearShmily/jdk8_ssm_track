package com.sample.common;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2017/2/13.
 */
public class activeMqCommon {
    //连接工厂
    private static ConnectionFactory connectionFactory = null;
    //连接
    private static Connection connection = null;
    //会话，接收或者发送消息的线程
    private static Session session;
    //消息的目的地,消息发送给谁.
    private static Destination destination;
    //消息生产者
    private static MessageProducer messageProducer;
    //消息的消费者
    private static MessageConsumer messageConsumer;
    //创建连接默认用户名和密码，url
    private static final String USERNAMW = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String LINK = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUMBER = 5;

    /**
     * 构造ConnectionFactory实例对象
     * @return
     */
    public static ConnectionFactory getConnectionFactory(){
        if(null == connectionFactory){
            connectionFactory = new ActiveMQConnectionFactory(USERNAMW,PASSWORD,LINK);
        }
        return connectionFactory;
    }

    public static void getQueue(String messageContext){
        try {
            // 构造从工厂得到连接对象
            connection = getConnectionFactory().createConnection();
            // 启动
            connection.start();
            // 获取操作连接(Boolean.TRUE表示加入了事务)
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            // 创建消息队列
            destination = session.createQueue("firstQueue");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            //发送消息
            send(session,messageProducer,messageContext);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            if(null != connection){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(null != session){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void send(Session session,MessageProducer messageProducer,String messageContext){
        for(int i=0;i<SENDNUMBER;i++){
            try {
                //创建消息发送方式
                TextMessage textMessage = session.createTextMessage(messageContext+":"+i);
                System.out.println("发送消息："+"activeMq发送消息，"+i);
                messageProducer.send(textMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
