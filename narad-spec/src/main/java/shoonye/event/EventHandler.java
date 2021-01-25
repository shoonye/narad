package shoonye.event;

import java.util.Map;

import shoonye.event.spec.Event;
import shoonye.event.spec.EventData;


public interface EventHandler<T extends EventData> {
	void handleEvent(Event<T> event, Map<String, String> properties) throws EventProcessingException;
	int getOrder();
	public String getKey();
}
