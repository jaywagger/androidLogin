package android.among.parents.child;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("childdao")
public class ChildDAOImpl implements ChildDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int childInsert(String parents_user_id, String name, String phone_number, String img) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("parents_user_id", parents_user_id);
		map.put("name", name);
		map.put("phone_number", phone_number);
		map.put("img", img);
		int result = sqlSession.insert("android.among.parents.child.childInsert",map);
		return result;
	}

	@Override
	public List<ChildDTO> childSelect(String parents_user_id) {
		return sqlSession.selectList("android.among.parents.child.childSelect", parents_user_id);
	}


}
