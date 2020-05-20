package android.among.parents.fcm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileUploadController {
   @RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
   @ResponseBody
   public Map<String, String> signDown(HttpServletRequest request) {
      try {
         request.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      String filename = request.getParameter("filename");
      String voices = request.getParameter("file");
      System.out.println(voices.substring(1, 100));
      System.out.println(voices);
      System.out.println("파일업로드");
      String path = "/WEB-INF/voices";
      String uploadPath = request.getServletContext().getRealPath(path);
      System.out.println("절대경로 : " + uploadPath + "<br/>");
      byte[] bytearr = binaryStringToByteArray(voices);
      try {
         File file = new File(uploadPath + "/" + filename);
         OutputStream output = new FileOutputStream(uploadPath + "/" + filename);
         output.write(bytearr);
         output.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

      Map<String, String> map = new HashMap<String, String>();
      return map;
   }

   public byte[] binaryStringToByteArray(String s) {
      int count = s.length() / 8;
      byte[] b = new byte[count];
      for (int i = 1; i < count; ++i) {
         String t = s.substring((i - 1) * 8, i * 8);
         b[i - 1] = binaryStringToByte(t);
      }
      return b;
   }

   public byte binaryStringToByte(String s) {
      byte ret = 0, total = 0;
      for (int i = 0; i < 8; ++i) {
         ret = (s.charAt(7 - i) == '1') ? (byte) (1 << i) : 0;
         total = (byte) (ret | total);
      }
      return total;
   }
}