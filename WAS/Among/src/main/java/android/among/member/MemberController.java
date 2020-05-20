package android.among.member;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String json) {
		MemberDTO dto;
		System.out.println(json);
		String result="false";
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = mapper.readValue(json, MemberDTO.class);
			System.out.println("*******"+dto);
			if(service.login(dto)!=null) {
				result = "true";
				System.out.println(result);
			}else{
				result = "false";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*@RequestMapping(value="/member/insert.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	public String insert(String userID, String name, String password, String phone, String birth, String gender, String token) {
		MemberDTO user = new MemberDTO(userID,name,password,phone,birth,gender,token);
		System.out.println(user);
		//service.insert(user);
		return "member";
	}*/
	
	@RequestMapping(value="/member/insert.do", method=RequestMethod.POST)
	public @ResponseBody String insert(@RequestBody String json) {
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		String str="0";
		try {
			MemberDTO dto = mapper.readValue(json, MemberDTO.class);
			System.out.println("=============="+dto);
			int result = service.insert(dto);
			if(result==1) {
				str="1";
			}else {
				str="0";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	@RequestMapping(value="/member/chk.do", method=RequestMethod.POST)
	public @ResponseBody String chkID(String userID) {
		System.out.println(userID);
		if(service.chkID(userID)==null) {
			System.out.println("중복되지 않은 ID");
			return "0";
		}else{
			System.out.println("존재하는 ID");
			return "1";
		}
	}
}
