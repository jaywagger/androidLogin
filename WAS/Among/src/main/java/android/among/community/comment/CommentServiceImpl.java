package android.among.community.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	@Qualifier("commentdao")
	CommentDAO dao;

	@Override
	public int commentInsert(String mcomment, String writedate, String userid, String board_num) {
		
		return dao.commentInsert(mcomment, writedate, userid, board_num);
	}

	@Override
	public List<CommentDTO> commentSelect(String seq) {
		return dao.commentSelect(seq);
	}

	@Override
	public int commentUpdate(String seq, String mcomment, String userid) {
		return dao.commentUpdate(seq, mcomment, userid);
	}

	@Override
	public int commentDelete(String seq, String userid) {
		return dao.commentDelete(seq, userid);
	}
	

}
