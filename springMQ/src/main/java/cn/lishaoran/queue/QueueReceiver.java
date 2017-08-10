package cn.lishaoran.queue;

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
 * ��Ϣ������
 * @author ALWZ
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class QueueReceiver {

	@Resource
	private JmsTemplate jmsTemplate;
	
	
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {	
		this.jmsTemplate = jmsTemplate;
	}

	//�������ļ���ȡ������Ϣ��ַ
	@Resource
	@Qualifier("queue")
	private Destination destination;

	
	@Test
	public void receiveMqMessage(){
		//Destination destination=jmsTemplate.getDefaultDestination();
		receive(destination);
	}
	
	
	//������Ϣ
	public void receive(Destination destination){
		TextMessage tm =(TextMessage)jmsTemplate.receive(destination);
		try {
			System.out.println("�Ӷ���"+destination.toString()+"�յ�����Ϣ��\t"+tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}


	
	
}