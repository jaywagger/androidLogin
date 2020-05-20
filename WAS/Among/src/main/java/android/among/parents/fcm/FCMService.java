package android.among.parents.fcm;


public interface FCMService {
	int getToken(String token);

	FCMDTO getClientToken(String id);
}
