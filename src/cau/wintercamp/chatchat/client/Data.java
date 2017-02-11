package cau.wintercamp.chatchat.client;

public class Data {
	
	public static final int DATATYPE_CHAT = 0, DATATYPE_LOGIN = 1, DATATYPE_REGISTER = 2;
	
	private int dataType;
	public Data(int dataType){
		this.dataType = dataType;
	}
	public final int getDataType() {
		return dataType;
	}
	
}
