package android.among.parents.fcm;

public class SendDataDTO {
	MessageDTO notification;
	String to;
	public SendDataDTO() {
		
	}
	public SendDataDTO(MessageDTO notification, String to) {
		super();
		this.notification = notification;
		this.to = to;
	}
	public MessageDTO getNotification() {
		return notification;
	}
	public void setNotification(MessageDTO notification) {
		this.notification = notification;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "SendDataDTO [notification=" + notification + ", to=" + to + "]";
	}
	
}
