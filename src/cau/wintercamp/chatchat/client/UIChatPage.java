package cau.wintercamp.chatchat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UIChatPage extends UIPage implements ActionListener{

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
	public JScrollPane scrollPane;
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
		scrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		titleLabel.setBounds(10,10,80,20);
		idLabel.setBounds(150, 10, 150, 20);
		logoutButton.setBounds(310, 10, 80, 20);
		chatArea.setBounds(10, 35, 380, 270);
		chatField.setBounds(10, 310, 300, 40);
		enterButton.setBounds(320, 310, 70, 40);
		scrollPane.setBounds(5, 35, 380, 270);
		
		chatArea.setEditable(false);
		
		this.add(titleLabel);
		this.add(idLabel);
		this.add(logoutButton);
		this.add(scrollPane);
		this.add(chatField);
		this.add(enterButton);
		
		this.enterButton.addActionListener(this);
		this.enterButton.setName("enter");
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton){
			JButton button = (JButton) event.getSource();
			if (button.getName().equals("enter")){
				System.out.println("User press enter button");
				String text = chatField.getText();
				if (text.length() > 0){
					DataChat data = new DataChat("room", text, userId);
					Thread thread = new Thread(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("Sending Thread: Start");
							main.client.sendData(data);
							System.out.println("Sending Thread: Success Send Message");
						}
					});
					thread.start();
					chatField.setText("");
				}
			}
		}
	}

	public void getChatDataFromClient(DataChat chat) {
		System.out.println("receive chat data");
		System.out.println(chat.getUserId() + ":" + chat.getMessage());
		String msg = chat.getUserId() + ":" + chat.getMessage() + "\n";
		chatArea.append(msg);
		
	}

}
