package shoonye.event;

import shoonye.event.spec.EventData;
import shoonye.event.spec.EventType;

public abstract class AbstractEventHandler<T extends EventData> implements EventHandler<T>, Comparable<AbstractEventHandler<T>>{
	protected int order;
	private String key;
	
	public AbstractEventHandler(String key, int order, EventType... eventTypes) {
        this.key = key;
        this.order = order;
        if(eventTypes!=null){
            for(EventType eventType: eventTypes){
                EventHandlerRegistry.register(eventType, this);
            }
        }     
    }
	
	@Override
	public int compareTo(AbstractEventHandler<T> other) {
		return order-other.order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEventHandler other = (AbstractEventHandler) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public int getOrder() {
		return order;
	}

	public String getKey() {
		return key;
	}
}
