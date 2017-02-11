package cau.wintercamp.chatchat.client;

public class DataRegister extends Data{

	public DataRegister(String id, String pw) {
		super(Data.DATATYPE_REGISTER);
	}
	
	public DataRegister(String id, String pw, boolean bool) {
		super(Data.DATATYPE_REGISTER);
	}

}
