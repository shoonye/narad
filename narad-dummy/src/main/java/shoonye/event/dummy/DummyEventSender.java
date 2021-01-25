package shoonye.event.dummy;

import org.springframework.stereotype.Service;

import shoonye.event.EventSender;
import shoonye.event.spec.Event;
import shoonye.event.spec.EventData;

@Service("eventSender")
public class DummyEventSender implements EventSender {

    @Override
    public <T extends EventData> void sendEvent(Event<T> event) {
        // TODO Auto-generated method stub

    }

	@Override
	public <T extends EventData> void sendEvent(String routingKey,
			Event<T> event) {
		// TODO Auto-generated method stub
		
	}

}
