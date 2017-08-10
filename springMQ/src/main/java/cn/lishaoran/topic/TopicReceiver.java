package cn.lishaoran.topic;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 消息接收者
 * @author ALWZ
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext--topic-consumer.xml"})
public class TopicReceiver {

	@Resource
	private JmsTemplate jmsTemplate;
	
	
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {	
		this.jmsTemplate = jmsTemplate;
	}

	//从配置文件获取接收消息地址
	@Resource
	@Qualifier("topic")
	private Destination destination;

	
	@Test
	public void receiveMqMessage(){
		//Destination destination=jmsTemplate.getDefaultDestination();
		receive(destination);
	}
	
	
	//接收消息
	public void receive(Destination destination){
		TextMessage tm =(TextMessage)jmsTemplate.receive(destination);
		try {
			System.out.println("从队列"+destination.toString()+"收到了消息：\t"+tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


	
	
}
