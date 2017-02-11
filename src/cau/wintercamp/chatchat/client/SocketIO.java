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
public class SocketIO {

	public Socket soc;
	public BufferedReader reader;
	public PrintWriter writer;
	
	public SocketIO(Socket soc){
		this.soc = soc;
	}
	
	/**
	 * 필수로 호출해야하는 메소드
	 * 변수들을 초기화 한다.
	 * @return 초기화가 제대로 되었는지 리턴한다
	 * */
	public boolean init(){
		try {
			this.reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			this.writer = new PrintWriter(soc.getOutputStream(), true);
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
			return reader.readLine();
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
		writer.println(raw);
		return raw;
	}
	
	/**
	 * 스트림을 닫아주는 메소드
	 * @return 스트림이 제대로 닫혔는지 boolean 리턴
	 * */
	public boolean closeStream(){
		try {
			writer.close();
			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}
