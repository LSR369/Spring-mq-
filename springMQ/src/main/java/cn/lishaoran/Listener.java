package cn.lishaoran;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener{

	public void onMessage(Message message) {
		TextMessage tm =(TextMessage) message;
		try {
			System.out.println("Listener�յ����ı���Ϣ��\t"+tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}