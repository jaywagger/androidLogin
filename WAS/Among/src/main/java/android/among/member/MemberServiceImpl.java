package android.among.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	@Qualifier("memberdao")
	MemberDAO dao;
	
	
	@Override
	public MemberDTO login(MemberDTO login) {
		return dao.login(login);
	}

	@Override
	public int insert(MemberDTO user) {
		return dao.insert(user);
	}

	@Override
	public MemberDTO chkID(String userID) {
		return dao.chkID(userID);
	}

}
