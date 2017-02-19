package cau.wintercamp.chatchat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 서버로부터 Raw 데이터를 가져오는 클래스
 * @author jo_mingyu
 * */
public class Client {

	//TODO  중간중간에 연결끊어진거 확인!
	public static Client client;
	public static void main(String[] args) {
		client = new Client();
	}
	
	public UIMain uiMain;
	public Socket soc;
	public SocketIO socIo;
	public Translator translator;
	public boolean isConnected = false;
	
	public String ip = "165.194.17.96";
	public int port = 7777;
	
	public Client(){
		
		Client me = this;
		uiMain = new UIMain(this);
		
		System.out.println("try to start thread: connect to server");
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!isConnected){
					System.out.println("Thread Run");
					try {
						soc = new Socket(ip, port);
						socIo = new SocketIO(me, soc);
						translator = new Translator();
						isConnected = true;
						System.out.println("Thread: connected to Server");
						
						if (socIo.init()) {
							System.out.println("Sucess Load Socket IO");
						}
						else {
							System.out.println("Failed Load Socket IO");
						}
						
					} catch (UnknownHostException e) {
						//e.printStackTrace();
					} catch (IOException e) {
						//e.printStackTrace();
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("try to change page");
				client.uiMain.loadingComplete();
			}
		});
		
		thread.start();
	}
	
	
	public String sendData(Data data){
		return socIo.sendRawData(translator.translateDataToJSONString(data));
	}
	
	public Data receiveData(){
		return translator.tranlateJSONToData(socIo.receiveRawData());
	}


	public void handleData(String receiveRawData) {
		Data data = translator.tranlateJSONToData(receiveRawData);
		switch (data.getDataType()){
		case Data.DATATYPE_REGISTER:
			this.uiMain.receiveRegisterData((DataRegister)data);
			return;
		case Data.DATATYPE_LOGIN:
			this.uiMain.receiveLoginData((DataLogin)data);
			return;
		case Data.DATATYPE_CHAT:
			this.uiMain.receiveChatData((DataChat)data);
			return;
		}
	}
	
	
}
