package cn.lishaoran.topic;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 消息发送者（topic模式）
 * @author ALWZ
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext--topic.xml"})
public class TopicSender{

	@Resource
	private JmsTemplate jmsTemplate;
	
	@Resource
	@Qualifier("topic")
	private Destination destination;
	
	@Test
	public void send(){
		sendMqMessage(destination,"测试发布订阅模式的发送消息内容： 发布到了地址destination:testSpringTopic！");
	}
	
	
	
	
	public void sendMqMessage(Destination destination,final String message){
		if (null==destination) {
			destination=jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination,new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		System.out.println("spring+mq 采用发布的订阅模式发送了消息！");
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
	    this.jmsTemplate = jmsTemplate;
	  }



}
