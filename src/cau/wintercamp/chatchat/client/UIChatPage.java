package cau.wintercamp.chatchat.client;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UIChatPage extends UIPage{

	private static final long serialVersionUID = 1L;

	public String userId;
	public UIChatPage(UIMain main, String userId) {
		super(main);
		this.userId = userId;
		init();
	}
	
	public JLabel titleLabel;
	public JLabel idLabel;
	public JButton logoutButton;
	public JTextArea chatArea;
	public JTextField chatField;
	public JButton enterButton;
	
	public void init(){
		
		this.setLayout(null);
		
		titleLabel = new JLabel("Chat!Chat!");
		idLabel = new JLabel(userId + " 님 환영합니다");
		logoutButton = new JButton("Log Out");
		chatArea = new JTextArea();
		chatField = new JTextField();
		enterButton = new JButton("입력");
		
		titleLabel.setBounds(10,10,80,20);
		idLabel.setBounds(150, 10, 150, 20);
		logoutButton.setBounds(310, 10, 80, 20);
		chatArea.setBounds(10, 35, 380, 270);
		chatField.setBounds(10, 310, 300, 40);
		enterButton.setBounds(320, 310, 70, 40);
		
		this.add(titleLabel);
		this.add(idLabel);
		this.add(logoutButton);
		this.add(chatArea);
		this.add(chatField);
		this.add(enterButton);
		
		
	}

}
