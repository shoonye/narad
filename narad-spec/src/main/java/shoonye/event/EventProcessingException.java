package shoonye.event;

public class EventProcessingException extends Exception{
	private static final long serialVersionUID = 1L;	
	private boolean tryToRecover;

	public EventProcessingException(boolean tryToRecover) {
		super();
		this.tryToRecover = tryToRecover;
	}
	
	public EventProcessingException(boolean tryToRecover, Exception e) {
		super(e);
		this.tryToRecover = tryToRecover;
	}

	public boolean isTryToRecover() {
		return tryToRecover;
	}

	public void setTryToRecover(boolean tryToRecover) {
		this.tryToRecover = tryToRecover;
	}	
}
