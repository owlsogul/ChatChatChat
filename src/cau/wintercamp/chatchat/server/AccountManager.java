package cau.wintercamp.chatchat.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AccountManager {

	public ServerManager serverManager;
	public File accountFolder;
	public String accountExtension = ".txt";
	public AccountManager(ServerManager serverManager) {
		this.serverManager = serverManager;
		accountFolder = new File(".\\account");
		accountFolder.mkdirs();
		Server.print("account manager", "create file " + accountFolder.getAbsolutePath());
	}
	
	public synchronized boolean isLoginSucceed(String id, String pw){
		File file = new File(accountFolder, id+accountExtension);
		if (file.exists()){
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				if (pw.equals(reader.readLine())){
					reader.close();
					return true;
				}
				else {
					reader.close();
				}
			} catch (FileNotFoundException e) {
				System.out.println(id + " failed login: non-file");
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public synchronized boolean createAccount(String id, String pw) {
		File file = new File(accountFolder, id+accountExtension);
		if (file.exists()){
			return false;
		}
		else {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				writer.write(pw);
				writer.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		}
	}

}
