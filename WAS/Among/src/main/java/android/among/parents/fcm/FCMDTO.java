package android.among.parents.fcm;

public class FCMDTO {
	String mem_id;
	String fcm_token;
	public FCMDTO() {
		// TODO Auto-generated constructor stub
	}
	public FCMDTO(String mem_id, String fcm_token) {
		super();
		this.mem_id = mem_id;
		this.fcm_token = fcm_token;
	}
	@Override
	public String toString() {
		return "FCMDTO [mem_id=" + mem_id + ", fcm_token=" + fcm_token + "]";
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getFcm_token() {
		return fcm_token;
	}
	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}
	
}
