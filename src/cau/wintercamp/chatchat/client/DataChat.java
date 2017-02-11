package cau.wintercamp.chatchat.client;

public class DataChat extends Data{

	private String roomId, message, userId;
	public DataChat(String roomId, String message, String userId) {
		super(Data.DATATYPE_CHAT);
		this.roomId = roomId;
		this.message = message;
		this.setUserId(userId);
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


}
