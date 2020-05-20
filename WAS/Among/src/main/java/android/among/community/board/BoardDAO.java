package android.among.community.board;

import java.util.List;

public interface BoardDAO {
	int boardInsert(String title, String text, String write_date, String userid);
	List<BoardDTO> boardSelect();
	int boardUpdate(String seq, String title, String text, String userid);
	int boardDelete(String seq, String userid);
}
