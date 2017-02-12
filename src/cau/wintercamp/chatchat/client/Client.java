package cau.wintercamp.chatchat.client;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * 서버로부터 Raw 데이터를 가져오는 클래스
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
		
		System.out.println("try to start thread: connect to server");
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!isConnected){
					System.out.println("Thread Run");
					try {
						soc = new Socket(ip, port);
						socIo = new SocketIO(soc);
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
					} catch (IOException e) {
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
	
	
}
