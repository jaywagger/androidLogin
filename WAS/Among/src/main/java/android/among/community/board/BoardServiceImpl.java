package android.among.community.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	@Qualifier("boarddao")
	BoardDAO dao;

	@Override
	public int boardInsert(String title, String text, String write_date, String userid) {
		
		return dao.boardInsert(title, text, write_date, userid);
	}

	@Override
	public List<BoardDTO> boardSelect() {
		return dao.boardSelect();
	}

	@Override
	public int boardUpdate(String seq, String title, String text, String userid) {
		return dao.boardUpdate(seq, title, text, userid);
	}

	@Override
	public int boardDelete(String seq, String userid) {
		return dao.boardDelete(seq, userid);
	}

}
