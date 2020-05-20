package android.among.parents.fcm;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("fcmdao")
public class FCMDAOImpl implements FCMDAO {
	@Autowired
	SqlSession sqlsession;

	@Override
	public FCMDTO getToken(String token) {
		System.out.println("============"+token);
		return sqlsession.selectOne("multi.shop.member.fcm.readToken", token);
	}

	@Override
	public int insert(String token) {
		// TODO Auto-generated method stubmulti.shop.member.fcm
		return sqlsession.insert("multi.shop.member.fcm.insertToken", token);
	}

	@Override
	public FCMDTO getClientToken(String id) {
		System.out.println("============"+id);
		return sqlsession.selectOne("multi.shop.member.fcm.readClientToken", id);
	}
	

}
