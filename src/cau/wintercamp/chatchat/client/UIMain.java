package cau.wintercamp.chatchat.client;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class UIMain extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public Client client;
	
	public UILoadingPage loadingPage;
	public UILoginPage loginPage;
	public UIChatPage  chatPage;
	
	public UIMain(Client client){
		
		super("ChatChat!");
		this.init();
		this.client = client;
	}
	
	
	public void init(){
		
		this.getContentPane().setLayout(new GridLayout(1, 1));
		
		this.loadingPage = new UILoadingPage(this);
		this.getContentPane().add(loadingPage);
		
		this.setVisible(true);
		this.setSize(400,400);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	public void loadingComplete(){
		
		this.getContentPane().remove(loadingPage);
		loadingPage = null;
		
		loginPage = new UILoginPage(this);
		this.getContentPane().add(loginPage);
		this.revalidate();
		this.repaint();
		
	}
	
	public String userId;
	public void loginComplete(String userId){
		System.out.println(userId + " 로 로그인이 완료되었습니다.");
		this.userId = userId;
		
		//For Test
		if (loadingPage != null){
			this.getContentPane().remove(loadingPage);
			loadingPage = null;
		}
		//
		
		if (loginPage != null){
			this.getContentPane().remove(loginPage);
			loginPage = null;
		}
		
		chatPage = new UIChatPage(this, userId);
		this.getContentPane().add(chatPage);
		this.revalidate();
		this.repaint();
	}


	public void receiveRegisterData(DataRegister data) {
		loginPage.getRegisterDataFromClient(data);
	}


	public void receiveLoginData(DataLogin data) {
		loginPage.getLoginDataFromClient(data);
	}


	public void receiveChatData(DataChat data) {
		
		chatPage.getChatDataFromClient(data);
		
	}


	public void receiveLogoutData(DataLogout data) {
		this.getContentPane().remove(chatPage);
		this.chatPage = null;
		this.userId = null;
		
		this.loginPage = new UILoginPage(this);
		this.getContentPane().add(loginPage);
		
		this.revalidate();
		this.repaint();
		
	}
	
}
