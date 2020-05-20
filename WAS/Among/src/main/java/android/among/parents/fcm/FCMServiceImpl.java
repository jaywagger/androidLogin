package android.among.parents.fcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class FCMServiceImpl implements FCMService {
	@Autowired
	@Qualifier("fcmdao")
	FCMDAO dao;

	@Override
	public int getToken(String token) {
		int result = 0;
		if(dao.getToken(token)==null) {
			System.out.println("토큰없어");
			result = dao.insert(token);
		}
		return result;
	}

	@Override
	public FCMDTO getClientToken(String id) {
		// TODO Auto-generated method stub
		return dao.getClientToken(id);
	}

	
	

}
