package shoonye.event.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

import shoonye.event.spec.Event;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

//TODO use jackson instead of JSON
public class EventMsgConverter implements MessageConverter {
	static final Logger logger = LoggerFactory.getLogger(EventMsgConverter.class);

	@Override
	public Event<?> fromMessage(Message msg) throws MessageConversionException {
		byte[] bytes = msg.getBody();
		String json = new String(bytes);
		
		Event<?> event = null;
		try {
			String className = (String)msg.getMessageProperties().getHeaders().get("EventDataClass");
			logger.debug(className);
			Class<?> eventDataClass = Class.forName(className);
			
			event =new JSONDeserializer<Event<?>>().use("data", eventDataClass)
//					.use(EventType.class, new EnumObjectFactory())
					.deserialize(json);
		} catch (Exception e) {
		    logger.error("Error pasring event {} ", json);
			logger.error(e.getMessage(),e);
			return null;
		}
		return event;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Message toMessage(Object object, MessageProperties mp) throws MessageConversionException {
	    logger.debug("To message {}", object );
		if(object instanceof Event){
			Event event = (Event) object;
			String json = new JSONSerializer().deepSerialize(event);
			mp.setHeader("EventDataClass", event.getData().getClass().getName());
			mp.setHeader("0-event-type", event.getEventType().getName());
			return new Message(json.getBytes(), mp);	
		}else{
			throw new MessageConversionException("Message is not of type Event");
		}		
	}
}
