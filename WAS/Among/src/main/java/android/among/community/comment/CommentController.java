package android.among.community.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CommentController {
	@Autowired
	CommentService service;
	
	@RequestMapping(value="/board/comment/insert", method=RequestMethod.POST)
	public @ResponseBody String commentInsert(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			CommentDTO dto = mapper.readValue(json, CommentDTO.class);
			System.out.println("====="+dto);
			int result = service.commentInsert(dto.getMcomment(), dto.getWritedate(), dto.getUser_id(), dto.getBoard_num());
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
	
	@RequestMapping(value="/board/comment/selectAll", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String commentSelect(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		CommentDTO dto;
		String result="";
		try {
			dto = mapper.readValue(json, CommentDTO.class);
			System.out.println("====="+dto);
			List<CommentDTO> list = service.commentSelect(dto.getBoard_num());
			
			result = mapper.writeValueAsString(list);
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
		return result;
	}
	
	@RequestMapping(value="/board/comment/update", method=RequestMethod.POST)
	public @ResponseBody String commentUpdate(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			CommentDTO dto = mapper.readValue(json, CommentDTO.class);
			System.out.println("====="+dto);
			int result = service.commentUpdate(dto.getComment_seq()+"", dto.getMcomment(), dto.getUser_id());
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
	
	@RequestMapping(value="/board/comment/delete", method=RequestMethod.POST)
	public @ResponseBody String commentDelete(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			CommentDTO dto = mapper.readValue(json, CommentDTO.class);
			System.out.println("====="+dto);
			int result = service.commentDelete(dto.getComment_seq()+"", dto.getUser_id());
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
