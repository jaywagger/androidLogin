package android.among.community.board;

public class BoardDTO {
	String board_seq;
	String title;
	String text;
	String write_date;
	String user_id;
	
	public BoardDTO() {
		
	}
	
	public BoardDTO(String board_seq, String userid) {
		this.board_seq = board_seq;
		this.user_id = user_id;
	}
	
	public BoardDTO(String board_seq, String title, String text, String user_id) {
		super();
		this.board_seq = board_seq;
		this.title = title;
		this.text = text;
		this.user_id = user_id;
	}
	
	public BoardDTO(String board_seq, String title, String text, String write_date, String user_id) {
		super();
		this.board_seq = board_seq;
		this.title = title;
		this.text = text;
		this.write_date = write_date;
		this.user_id = user_id;
	}

	public String getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(String board_seq) {
		this.board_seq = board_seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	
}
	