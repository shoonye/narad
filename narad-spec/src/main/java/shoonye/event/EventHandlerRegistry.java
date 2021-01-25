package shoonye.event;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shoonye.event.spec.EventData;
import shoonye.event.spec.EventType;


@SuppressWarnings("unchecked")
public class EventHandlerRegistry {
	static final Logger logger = LoggerFactory.getLogger(EventHandlerRegistry.class);
	private static HashMap<EventType, TreeSet<?>> registry = new HashMap<EventType, TreeSet<?>>();
	
	public static synchronized <T extends EventData> void register(EventType type, EventHandler<T> eventHandler){
		TreeSet<EventHandler<T>> set = (TreeSet<EventHandler<T>>)registry.get(type);
		if(set==null) set = new TreeSet<EventHandler<T>>();
		set.add(eventHandler);
		registry.put(type, set);
		logger.info("registered ["+eventHandler.getOrder()+"] " + eventHandler.getClass().getName());
	}

	public static <T extends EventData> Set<EventHandler<T>> registeredHandlers(EventType type) {
		return (TreeSet<EventHandler<T>>)registry.get(type);
	}	
}
