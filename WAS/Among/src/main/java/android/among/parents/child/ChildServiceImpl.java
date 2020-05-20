package android.among.parents.child;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChildServiceImpl implements ChildService {
	@Autowired
	@Qualifier("childdao")
	ChildDAO dao;

	@Override
	public int childInsert(String parents_user_id, String name, String phone_number, String img) {
		
		return dao.childInsert(parents_user_id, name, phone_number, img);
	}

	@Override
	public List<ChildDTO> childSelect(String parents_user_id) {
		return dao.childSelect(parents_user_id);
	}

}
