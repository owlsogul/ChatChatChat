package cau.wintercamp.chatchat.server;

public class DataChat {

	String roomId;
	String message;
	String userId;
	
	public DataChat(String roomId, String message, String userId) {
		this.roomId = roomId;
		this.message = message;
		this.userId = userId;
	}
}
