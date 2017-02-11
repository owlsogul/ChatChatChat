package cau.wintercamp.chatchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * ������ ������ �ְ�/�޴� Ŭ����
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
	 * �ʼ��� ȣ���ؾ��ϴ� �޼ҵ�
	 * �������� �ʱ�ȭ �Ѵ�.
	 * @return �ʱ�ȭ�� ����� �Ǿ����� �����Ѵ�
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
	 * �����κ��� �����͸� �������� �޼ҵ�
	 * @return �����κ��� ������ String
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
	 * ������ �����͸� ������ �޼ҵ�
	 * @return ������ ���� String
	 * */
	public String sendRawData(String raw){
		writer.println(raw);
		return raw;
	}
	
	/**
	 * ��Ʈ���� �ݾ��ִ� �޼ҵ�
	 * @return ��Ʈ���� ����� �������� boolean ����
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
