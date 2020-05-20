package android.among.community.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boarddao")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int boardInsert(String title, String text, String write_date, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("text", text);
		map.put("write_date", write_date);
		map.put("userid", userid);
		int result = sqlSession.insert("android.among.community.board.boardInsert",map);
		return result;
	}

	@Override
	public List<BoardDTO> boardSelect() {
		return sqlSession.selectList("android.among.community.board.boardSelect");
	}

	@Override
	public int boardUpdate(String seq, String title, String text, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("title", title);
		map.put("text", text);
		map.put("userid", userid);
		int result = sqlSession.insert("android.among.community.board.boardUpdate",map);
		return result;
	}

	@Override
	public int boardDelete(String seq, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq);
		map.put("userid", userid);
		int result = sqlSession.delete("android.among.community.board.boardDelete", map);
		return result;
	}


}
