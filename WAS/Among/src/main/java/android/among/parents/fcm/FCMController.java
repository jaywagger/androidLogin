package android.among.parents.fcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FCMController {
	@Autowired
	FCMService service;
	@RequestMapping(value = "/fcm/fcm_check", method = RequestMethod.GET)
	public String getToken(String token) {
		int result = service.getToken(token);
		if(result==1) {
			System.out.println("저장완료");
		}
		return "redirect:/index.do";
	}
	@RequestMapping(value = "/fcm/sendClient", method = RequestMethod.GET)
	public String sendMessage(String id) {
		FCMDTO result = service.getClientToken("2");
		String apiKey = "AAAADm0HBUI:APA91bGU0X1pVOS_MXQJBC9Psrk2lZGsVz6ciQCN6U7TdNBovC8i26HYmtIavhH84Zk8gaKnCnJEpRwmTrPPcBukjbFXAzlAMf1_97XvQIEUEH-T2IGvm08pcIbYCJIx_5l7lA1PhMjk";
		try {
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/json");
			connection.setRequestProperty("Authorization","key="+apiKey);
			
			MessageDTO msg = new MessageDTO("FCM테스트","order");
			SendDataDTO senddto = new SendDataDTO(msg, result.getFcm_token());
			//메시지 정보를 셋팅한다.
			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(senddto);
			System.out.println("변환===> "+jsonString);
			//서버로 데이터 전달하기
			OutputStream os = connection.getOutputStream();
			os.write(jsonString.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			//firebase서버가 전달하는 응답메시지 받기
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			System.out.println("br====>"+br);
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				sb.append(line);
			}
			br.close();
			System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/index.do";
	}
}







