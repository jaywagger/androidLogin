package android.among.parents.fcm;


public interface FCMDAO {
	FCMDTO getToken(String token);
	int insert(String token);
	FCMDTO getClientToken(String id);
}