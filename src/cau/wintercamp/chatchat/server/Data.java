package cau.wintercamp.chatchat.server;

public class Data {
	
	int dataType;
	String roomId;
	String message;
	boolean same;
	String id;
	String pw;
	
	
	
	public void dataChat(String roomId, String message, String userId){
		
		this.roomId = roomId;
		this.message = message;
		this.id = userId;
	}
	
	public void dataRegister(String id, String pw, boolean same){
		this.id = id;
		this.pw = pw;
		this.same = same;
	}
	
	public void dataLogin(String id, String pw, boolean same){
		this.id = id;
		this.pw = pw;
		this.same = same;
	}
	
	public void dataLogout(String id){
		this.id = id;
	}
}
