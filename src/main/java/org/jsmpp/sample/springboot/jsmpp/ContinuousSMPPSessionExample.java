package org.jsmpp.sample.springboot.jsmpp;

import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;

public class ContinuousSMPPSessionExample {

	public static void main(String[] args) {
		try {
			SMPPSession session = new SMPPSession();
			session.connectAndBind("localhost", 2075,  
					new BindParameter(BindType.BIND_TX, "username", "password","cp", TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
			System.err.println("Connected with SMSC with system id :" +  session);

			// Gửi tin nhắn
			int stt = 0 ;
			while(true) {
				
				String messageId = null;
			    messageId = session.submitShortMessage("CMT", TypeOfNumber.INTERNATIONAL,
						NumberingPlanIndicator.UNKNOWN, "sender", TypeOfNumber.INTERNATIONAL,
						NumberingPlanIndicator.UNKNOWN, "recipient", new ESMClass(), (byte) 0, (byte) 1, null, null,
						new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT), (byte) 0,
						new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false), (byte) 0,
						"Hello, jSMPP!".getBytes());

				System.err.println("Message submitted stt : "+ stt + ", message_id is " + messageId);
				stt++;
				 Thread.sleep(3000);
//				session.unbindAndClose();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
