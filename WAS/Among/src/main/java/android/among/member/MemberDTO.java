package android.among.member;

public class MemberDTO {
	private String idx;
	private String userID;
	private String name;
    private String password;
    private String phone;
    private String birth;
    private String gender;
    private String token;
    
    public MemberDTO() {
    	
    }
    
	public MemberDTO(String userID) {
		super();
		this.userID = userID;
	}

	public MemberDTO(String userID, String password) {
		super();
		this.userID = userID;
		this.password = password;
	}

	public MemberDTO(String userID, String name, String password, String phone, String birth, String gender,
			String token) {
		super();
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.token = token;
	}
	
	public MemberDTO(String idx, String userID, String name, String password, String phone, String birth, String gender,
			String token) {
		super();
		this.idx = idx;
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.token = token;
	}

	@Override
	public String toString() {
		return "MemberDTO [idx=" + idx + ", userID=" + userID + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", birth=" + birth + ", gender=" + gender + ", token=" + token + "]";
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}
    
    
}