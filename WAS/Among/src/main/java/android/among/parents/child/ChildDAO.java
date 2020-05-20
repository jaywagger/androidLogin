package android.among.parents.child;

import java.util.List;

public interface ChildDAO {
	int childInsert(String parents_user_id, String name, String phone_number, String img);
	List<ChildDTO> childSelect(String parents_user_id);
}
