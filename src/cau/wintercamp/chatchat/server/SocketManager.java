package cau.wintercamp.chatchat.server;

import java.net.Socket;

public class SocketManager{


	public SocketManager(Socket socket) {
		
		// �Ѱܹ��� socket�� SocketThread�� �����ϸ鼭 ������ ����
		Thread thread = new Thread(new SocketThread(socket));
		thread.start();
	}
	
	// ���ϵ� �����ϴ°� ¥��
}
