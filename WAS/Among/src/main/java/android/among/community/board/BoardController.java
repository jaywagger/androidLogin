package android.among.community.board;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	@RequestMapping(value="/board/insert", method=RequestMethod.POST)
	public @ResponseBody String boardInsert(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			BoardDTO dto = mapper.readValue(json, BoardDTO.class);
			System.out.println("====="+dto);
			int result = service.boardInsert(dto.getTitle(), dto.getText(), dto.getWrite_date(), dto.getUser_id());
			if(result==1) {
				str = "true";
			}else {
				str = "false";
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	@RequestMapping(value="/board/selectAll", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody List<BoardDTO> boardSelect() {
		String result = "";
		System.out.println("jjjjjj");
		List<BoardDTO> list = service.boardSelect();
		return list;
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public @ResponseBody String boardUpdate(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			BoardDTO dto = mapper.readValue(json, BoardDTO.class);
			System.out.println("====="+dto);
			int result = service.boardUpdate(dto.getBoard_seq()+"", dto.getTitle(), dto.getText(), dto.getUser_id());
			if(result==1) {
				str = "true";
			}else {
				str = "false";
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public @ResponseBody String boardDelete(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			BoardDTO dto = mapper.readValue(json, BoardDTO.class);
			System.out.println("====="+dto);
			int result = service.boardDelete(dto.getBoard_seq()+"", dto.getUser_id());
			if(result==1) {
				str = "true";
			}else {
				str = "false";
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
}
