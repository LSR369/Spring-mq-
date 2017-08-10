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
 * ��Ϣ�����ߣ�queueģʽ��
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
		sendMqMessage(destination,"���Ե�Ե�ģʽ�ķ�����Ϣ����+����Ȼ��ã�");
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
		System.out.println("spring+mq ���õ�Ե� ��������Ϣ��");
	}
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
	    this.jmsTemplate = jmsTemplate;
	  }



}