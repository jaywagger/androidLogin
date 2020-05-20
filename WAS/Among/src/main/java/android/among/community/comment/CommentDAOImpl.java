package android.among.community.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("commentdao")
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int commentInsert(String mcomment, String writedate, String userid, String board_num) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mcomment", mcomment);
		map.put("writedate", writedate);
		map.put("userid", userid);
		map.put("board_num",board_num);
		int result = sqlSession.insert("android.among.community.comment.commentInsert",map);
		return result;
	}

	@Override
	public List<CommentDTO> commentSelect(String seq) {
		return sqlSession.selectList("android.among.community.comment.commentSelect", seq);
	}

	@Override
	public int commentUpdate(String seq, String mcomment, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("mcomment", mcomment);
		map.put("userid", userid);
		int result = sqlSession.insert("android.among.community.comment.commentUpdate",map);
		return result;
	}

	@Override
	public int commentDelete(String seq, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("userid", userid);
		int result = sqlSession.delete("android.among.community.comment.commentDelete", map);
		return result;
	}


}
