package week11.kristian.id.ac.umn;

public class AuthenticationException extends Exception{
	public AuthenticationException () {
		super("Anda telah menccapai jumlah batas login");
	}
	
	public AuthenticationException(String msg) {
		super(msg);
	}
}
