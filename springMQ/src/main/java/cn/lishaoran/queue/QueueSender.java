package cn.lishaoran.queue;

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
 * 消息发送者（queue模式）
 * @author ALWZ
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class QueueSender{

	@Resource
	private JmsTemplate jmsTemplate;
	
	@Resource
	@Qualifier("queue")
	private Destination destination;
	
	@Test
	public void send(){
		sendMqMessage(destination,"测试点对点模式的发送消息内容+李少然你好！");
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
		System.out.println("spring+mq 采用点对点 发送了消息！");
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
	    this.jmsTemplate = jmsTemplate;
	  }



}
