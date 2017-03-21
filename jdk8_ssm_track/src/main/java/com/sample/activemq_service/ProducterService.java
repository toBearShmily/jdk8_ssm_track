package com.sample.activemq_service;

import javax.jms.Destination;

/**
 * Created by Administrator on 2017/2/15.
 */
public interface ProducterService {
    public void sendMessage(Destination destination,String message);
}
