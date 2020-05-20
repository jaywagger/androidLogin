package android.among.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("memberdao")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public MemberDTO login(MemberDTO login) {
		MemberDTO mem = sqlSession.selectOne("android.among.member.login", login);
		System.out.println(mem);
		return mem;
	}

	@Override
	public int insert(MemberDTO user) {
		int result = sqlSession.insert("android.among.member.memInsert",user);
		return result;
	}

	@Override
	public MemberDTO chkID(String userID) {
		return sqlSession.selectOne("android.among.member.chk", userID);
	}

}
