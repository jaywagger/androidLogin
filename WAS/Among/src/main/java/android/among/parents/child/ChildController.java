package android.among.parents.child;

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

import android.among.community.comment.CommentDTO;

@Controller
public class ChildController {
	@Autowired
	ChildService service;
	
	@RequestMapping(value="/child/insert", method=RequestMethod.POST)
	public @ResponseBody String childInsert(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="";
		try {
			ChildDTO dto = mapper.readValue(json, ChildDTO.class);
			System.out.println("====="+dto);
			int result = service.childInsert(dto.getParents_user_id(), dto.getName(), dto.getPhone_number(), dto.getImg()+"");
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
	
	@RequestMapping(value="/child/selectAll", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String childSelect(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		ChildDTO dto;
		String result="";
		try {
			dto = mapper.readValue(json, ChildDTO.class);
			System.out.println("====="+dto);
			List<ChildDTO> list = service.childSelect(dto.getParents_user_id());
			
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

	
}
