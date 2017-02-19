package cau.wintercamp.chatchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * 서버와 데이터 주고/받는 클래스
 * @author owlsogul
 *
 */
public class SocketIO implements Runnable{

	public Socket soc;
	public Client client;
	public BufferedReader reader;
	public PrintWriter writer;
	public Thread readerThread;
	
	public SocketIO(Client client, Socket soc){
		this.client = client;
		this.soc = soc;
	}
	
	/**
	 * 필수로 호출해야하는 메소드
	 * 변수들을 초기화 한다.
	 * @return 초기화가 제대로 되었는지 리턴한다
	 * */
	public boolean init(){
		try {
			this.readerThread = new Thread(this);
			this.reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			this.writer = new PrintWriter(soc.getOutputStream(), true);
			this.readerThread.start();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 서버로부터 데이터를 가져오는 메소드
	 * @return 서버로부터 가져온 String
	 * */
	public String receiveRawData() {
		try {
			String ret = reader.readLine();
			System.out.println("SocketIO: Read " + ret);
			return ret;
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 서버로 데이터를 보내는 메소드
	 * @return 서버로 보낸 String
	 * */
	public String sendRawData(String raw){
		System.out.println("SocketIO: Send " + raw);
		writer.println(raw);
		writer.flush();
		return raw;
	}
	
	/**
	 * 스트림을 닫아주는 메소드
	 * @return 스트림이 제대로 닫혔는지 boolean 리턴
	 * */
	public boolean closeStream(){
		try {
			System.out.println("SocketIO: Close");
			readerThread.interrupt();
			writer.close();
			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void run() {
		while (true){
			try {
				System.out.println("Runnig " + reader.ready());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String some = receiveRawData();
			client.handleData(some);
		}
		
	}
	
	
	
}
