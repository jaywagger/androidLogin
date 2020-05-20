package android.among.community.comment;

import java.util.List;

public interface CommentDAO {
	int commentInsert(String mcomment, String writedate, String userid, String board_num);
	List<CommentDTO> commentSelect(String seq);
	int commentUpdate(String seq, String mcomment, String userid);
	int commentDelete(String seq, String userid);
}
