package shoonye.event.simple;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import shoonye.event.EventHandler;
import shoonye.event.EventHandlerRegistry;
import shoonye.event.EventProcessingException;
import shoonye.event.EventSender;
import shoonye.event.spec.Event;
import shoonye.event.spec.EventData;
import shoonye.util.CollectionUtil;

@Service("eventSender")
public class SimpleEventSender implements EventSender {
    static final Logger logger = LoggerFactory.getLogger(SimpleEventSender.class);
	
	@Override
	@Async
	public <T extends EventData> void sendEvent(Event<T> event) {
	    Set<EventHandler<T>> handlers = EventHandlerRegistry.registeredHandlers(event.getEventType());
	    if(CollectionUtil.hasItems(handlers)){
	        for(EventHandler<T> handler: handlers){
	            try {
                    handler.handleEvent(event, null);
                } catch (EventProcessingException e) {
                    // TODO  retry?
                    logger.error(e.getMessage(), e);
                }
	        }
	    }
	}

	@Override
	public <T extends EventData> void sendEvent(String routingKey,
			Event<T> event) {
		// TODO Auto-generated method stub
		
	}
}
