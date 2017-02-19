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
	 * �ʼ��� ȣ���ؾ��ϴ� �޼ҵ�
	 * �������� �ʱ�ȭ �Ѵ�.
	 * @return �ʱ�ȭ�� ����� �Ǿ����� �����Ѵ�
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
	 * �����κ��� �����͸� �������� �޼ҵ�
	 * @return �����κ��� ������ String
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
	 * ������ �����͸� ������ �޼ҵ�
	 * @return ������ ���� String
	 * */
	public String sendRawData(String raw){
		System.out.println("SocketIO: Send " + raw);
		writer.println(raw);
		writer.flush();
		return raw;
	}
	
	/**
	 * ��Ʈ���� �ݾ��ִ� �޼ҵ�
	 * @return ��Ʈ���� ����� �������� boolean ����
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
