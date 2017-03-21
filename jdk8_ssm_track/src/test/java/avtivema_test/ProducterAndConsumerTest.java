package avtivema_test;

import com.sample.activemq_service.ProducterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * Created by Administrator on 2017/2/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring/spring.xml")
public class ProducterAndConsumerTest {

    @Autowired
    private ProducterService producterService;

    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void send(){
        //for(int i=0;i<2;i++){
            producterService.sendMessage(destination,"生产者发消息啦（我是）");
        //}
    }

}
