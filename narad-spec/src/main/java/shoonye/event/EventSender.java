package shoonye.event;

import shoonye.event.spec.Event;
import shoonye.event.spec.EventData;

public interface EventSender {
	public <T extends EventData> void sendEvent(Event<T> event);
	public <T extends EventData> void sendEvent(String routingKey, Event<T> event);
}
