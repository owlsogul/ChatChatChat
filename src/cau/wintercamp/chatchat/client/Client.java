package cau.wintercamp.chatchat.client;

import java.io.IOException;
import java.net.Socket;

/**
 * �����κ��� Raw �����͸� �������� Ŭ����
 * @author jo_mingyu
 * */
public class Client {

	public static Client client;
	public static void main(String[] args) {
		client = new Client();
	}
	
	public UIMain uiMain;
	
	public Socket soc;
	public SocketIO socIo;
	public Translator translator;
	public boolean isConnected = false;
	
	public String ip = "localhost";
	public int port = 7777;
	
	public Client(){
		
		uiMain = new UIMain(this);
		
		try {
			//TODO: ������ ��Ȥ Ȯ���ϴ� ������ �����
			soc = new Socket(ip, port);
			socIo = new SocketIO(soc);
			translator = new Translator();
			isConnected = true;
			if (socIo.init()) {
				System.out.println("Sucess Load Socket IO");
			}
			else {
				System.out.println("Failed Load Socket IO");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String sendData(Data data){
		return socIo.sendRawData(translator.translateDataToJSONString(data));
	}
	
	public Data receiveData(){
		return translator.tranlateJSONToData(socIo.receiveRawData());
	}
	
	
}
