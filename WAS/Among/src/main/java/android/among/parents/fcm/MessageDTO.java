package android.among.parents.fcm;

public class MessageDTO {
	String title;
	String body;
	public MessageDTO() {
		
	}
	public MessageDTO(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MessageDTO [title=" + title + ", body=" + body + "]";
	}
	
}
