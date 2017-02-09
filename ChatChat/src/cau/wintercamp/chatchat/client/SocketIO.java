package cau.wintercamp.chatchat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketIO {

	public Socket soc;
	public BufferedReader reader;
	public PrintWriter writer;
	
	public SocketIO(Socket soc){
		this.soc = soc;
	}
	
	
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
	
	public String readRawData() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String writeRawData(String raw){
		writer.println(raw);
		return raw;
	}
	
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
