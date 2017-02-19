package cau.wintercamp.chatchat.client;

public class DataLogout extends Data {

	private String userId;
	public DataLogout(String userId) {
		super(Data.DATATYPE_LOGOUT);
		this.userId = userId;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
