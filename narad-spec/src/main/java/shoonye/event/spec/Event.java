package shoonye.event.spec;

import java.util.Date;

public class Event<T extends EventData>{
	private EventType eventType;
	private T data;
	private String eventId;
	private Date createTime;
	private int tryCount=0;
	private String appCode;
	
	public Event(){}
	
	public Event(EventType eventType, T data) {
		this.eventType = eventType;
		this.data = data;
		this.createTime = new Date();
	}
	
	public EventType getEventType() {
		return eventType;
	}
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getTryCount() {
		return tryCount;
	}
	public void setTryCount(int tryCount) {
		this.tryCount = tryCount;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

    @Override
    public String toString() {
        return "Event [eventType=" + eventType + ", data=" + data + ", eventId=" + eventId + ", createTime="
                + createTime + ", tryCount=" + tryCount + ", appCode=" + appCode + "]";
    }
	
}
