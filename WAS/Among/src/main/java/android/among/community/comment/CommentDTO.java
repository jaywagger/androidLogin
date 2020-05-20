package android.among.community.comment;

public class CommentDTO {
	private String comment_seq;
	private String mcomment;
	private String writedate;
	private String user_id;
	private String board_num;
	
	public CommentDTO(){
		
	}

	//select
    public CommentDTO(String board_num){
        this.board_num = board_num;
    }
	
	//delete
    public CommentDTO(String comment_seq, String user_id){
        this.comment_seq = comment_seq;
        this.user_id = user_id;
    }
    //insert
    public CommentDTO(String mcomment, String writedate, String user_id, String board_num) {
        this.mcomment = mcomment;
        this.writedate = writedate;
        this.user_id = user_id;
        this.board_num = board_num;
    }
    
    //update
    public CommentDTO(String comment_seq, String mcomment, String user_id) {
        this.comment_seq = comment_seq;
        this.mcomment = mcomment;
        this.user_id = user_id;
    }
	
	public CommentDTO(String comment_seq, String mcomment, String writedate, String user_id, String board_num) {
		super();
		this.comment_seq = comment_seq;
		this.mcomment = mcomment;
		this.writedate = writedate;
		this.user_id = user_id;
		this.board_num = board_num;
	}

	public String getComment_seq() {
		return comment_seq;
	}

	public void setComment_seq(String comment_seq) {
		this.comment_seq = comment_seq;
	}

	public String getMcomment() {
		return mcomment;
	}

	public void setMcomment(String mcomment) {
		this.mcomment = mcomment;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUserid(String user_id) {
		this.user_id = user_id;
	}

	public String getBoard_num() {
		return board_num;
	}

	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
