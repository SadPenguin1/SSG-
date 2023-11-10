/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.jsmpp.sample.springboot.jsmpp;

import static org.jsmpp.SMPPConstant.STAT_ESME_RSYSERR;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.CancelSm;
import org.jsmpp.bean.DataSm;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.QuerySm;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.ReplaceSm;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.SubmitMulti;
import org.jsmpp.bean.SubmitMultiResult;
import org.jsmpp.bean.SubmitSm;
import org.jsmpp.bean.SubmitSmResp;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ProcessRequestException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.extra.SessionState;
import org.jsmpp.sample.springboot.connection.ConnectionProperties;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.DataSmResult;
import org.jsmpp.session.QuerySmResult;
import org.jsmpp.session.SMPPServerSession;
import org.jsmpp.session.ServerMessageReceiverListener;
import org.jsmpp.session.Session;
import org.jsmpp.util.MessageIDGenerator;
import org.jsmpp.util.MessageId;
import org.jsmpp.util.RandomMessageIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerMessageReceiverListenerImpl implements ServerMessageReceiverListener {

	private MessageIDGenerator messageIDGenerator;
	private Charset charset;
//	SMPPSessionCustom session;
	private int sessionIndex;
	private SubmitMultiResult submitMultiResult;

	@Autowired
	SmppClientService2 smppClientService2;
	
	@Autowired 
	ServerResponseDeliveryListenerImpl serverResponseDeliveryListenerImpl;

	private List<SMPPSessionCustom> smppSessions = new ArrayList<>();

	public ServerMessageReceiverListenerImpl() throws IOException {
		this.charset = StandardCharsets.ISO_8859_1;
		messageIDGenerator = new RandomMessageIDGenerator();
		log.info("SMSC charset is {}", charset.name());
		
//		for (int i = 0; i < 20; i++) {
//			SMPPSessionCustom session = new SMPPSessionCustom(new ConnectionProperties());
//			try {
//				final String systemId = session.connectAndBind("localhost", 8056, new BindParameter(BindType.BIND_TX,
//						"j", "jpwd", "cp", TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, null));
//				smppSessions.add(session);
//			} catch (IOException | IllegalArgumentException e) {
//				log.error("Failed to connect and bind SMPP session.", e);
//			}
//		}
		
	}

//	private SMPPSessionCustom getNextAvailableSession() {
//		if (!smppSessions.isEmpty()) {
//			int currentIndex = sessionIndex % smppSessions.size();
//			sessionIndex++;
//			return smppSessions.get(currentIndex);
//		} else {
//			log.error("No available SMPP sessions.");
//			return null;
//		}
//	}

	public MessageId onAcceptSubmitSm(SubmitSm submitSm, SMPPServerSession source ) throws ProcessRequestException {
		final MessageId messageId = messageIDGenerator.newMessageId();
		System.err.println(" messsageId : " +messageId + " ," +submitSm.getSourceAddr()+" ," + submitSm.getDestAddress()+ " ," + submitSm.getSourceAddr() + ", noi dung : " +new String(submitSm.getShortMessage(), charset));
		// Log ra thông tin tin nhắn
		log.info("Session {} received '{}' message id:{}", source.getSessionId(),
				new String(submitSm.getShortMessage(), charset), messageId);
	
//		serverResponseDeliveryListenerImpl.onSubmitSmRespSent(submitSm.getSmDefaultMsgId(), source);
	
//		System.err.println(submitSm.getServiceType());
//		System.err.println(submitSm.getSourceAddrTon());
//		System.err.println(submitSm.getDestAddrTon());
//		System.err.println(submitSm.getDestAddrNpi());
//		System.err.println(submitSm.getDestAddress());
//		System.err.println(submitSm.getSourceAddr());

//		System.err.println(new String(submitSm.getShortMessage(), charset));
//		System.err.println(source.getSessionId());
//		 SMPPSessionCustom session = getNextAvailableSession();

//		try {
//			SMPPSessionCustom session = getNextAvailableSession();
//			SMPPSessionCustom session = new SMPPSessionCustom(new ConnectionProperties());
//

//			 try {
//	                final String systemId = session.connectAndBind("localhost", 8056,
//	                        new BindParameter(BindType.BIND_TX, "j", "jpwd", "cp", TypeOfNumber.UNKNOWN,
//	                                NumberingPlanIndicator.UNKNOWN, null));
//	                smppSessions.add(session);
//	            } catch (IOException | IllegalArgumentException e) {	
//	                log.error("Failed to connect and bind SMPP session.", e);
//	            }
//
//			SubmitSmResp submitSmResp = session.submitShortMessageGetResp(submitSm.getServiceType(),
//					TypeOfNumber.ABBREVIATED, NumberingPlanIndicator.UNKNOWN, submitSm.getSourceAddr(),
//					TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.ISDN, submitSm.getDestAddress(), new ESMClass(),
//					(byte) 0, (byte) 1, null, null, new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT), (byte) 0,
//					new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false), (byte) 0,
//					submitSm.getShortMessage());

//			session.unbindAndClose();

//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (PDUException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ResponseTimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidResponseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NegativeResponseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	}
		return messageId;
	}

	public DataSmResult onAcceptDataSm(final DataSm dataSm, final Session source) throws ProcessRequestException {
		log.info("Received data_sm");
		throw new ProcessRequestException("The data_sm is not implemented", STAT_ESME_RSYSERR);
	}

	public SubmitMultiResult onAcceptSubmitMulti(final SubmitMulti submitMulti, final SMPPServerSession source)
			throws ProcessRequestException {
		log.info("Received submit_multi");
		final MessageId messageId = messageIDGenerator.newMessageId();
		return new SubmitMultiResult(messageId.getValue());
	}

	public QuerySmResult onAcceptQuerySm(QuerySm querySm, SMPPServerSession source) throws ProcessRequestException {
		log.info("Received query_sm");
		throw new ProcessRequestException("The replace_sm is not implemented", STAT_ESME_RSYSERR);
	}

	public void onAcceptReplaceSm(ReplaceSm replaceSm, SMPPServerSession source) throws ProcessRequestException {
		log.info("Received replace_sm");
		throw new ProcessRequestException("The replace_sm is not implemented", STAT_ESME_RSYSERR);
	}

	public void onAcceptCancelSm(CancelSm cancelSm, SMPPServerSession source) throws ProcessRequestException {
		log.info("Received cancel_sm");
		throw new ProcessRequestException("The cancel_sm is not implemented", STAT_ESME_RSYSERR);
	}

}
