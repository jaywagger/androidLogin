package android.among.member;

public interface MemberDAO {
	MemberDTO login(MemberDTO login);
	int insert(MemberDTO user);
	MemberDTO chkID(String userID);
}
