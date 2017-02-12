package cau.wintercamp.chatchat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UILoginPage extends UIPage implements ActionListener{
	
	public UILoginPage(UIMain main) {
		super(main);
		init();
	}

	private static final long serialVersionUID = 1L;
	
	public JLabel idLabel, pwLabel;
	public JTextField idField, pwField;
	
	public JButton signInButton;
	public boolean canClickSignIn = true;
	
	public JButton signUpButton;
	public boolean canClickSignUp = true;
	
	public String strSignIn = "Sign In";
	public String strSignUp = "Sign Up";
	
	public void init(){
		
		System.out.println("initiate Login Page");
		this.setLayout(null);
		
		idLabel = new JLabel("ID");
		pwLabel = new JLabel("PW");
		idField = new JTextField();
		pwField = new JTextField();
		signInButton = new JButton(strSignIn);
		signUpButton = new JButton(strSignUp);
		
		idLabel.setBounds(10, 10, 40, 30);
		idField.setBounds(50, 10, 150, 30);
		
		pwLabel.setBounds(10, 50, 40, 30);
		pwField.setBounds(50, 50, 150, 30);
		
		signInButton.setBounds(205, 10, 90, 70);
		signUpButton.setBounds(300, 10, 90, 70);
		signInButton.addActionListener(this);
		signUpButton.addActionListener(this);
		
		this.add(idLabel);
		this.add(idField);
		this.add(pwField);
		this.add(pwLabel);
		this.add(signInButton);
		this.add(signUpButton);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() instanceof JButton){
			JButton button = (JButton) event.getSource();
			if (button.getText().equals(strSignIn)){
				if (canClickSignIn &&
						idField.getText().length() > 3 &&
						pwField.getText().length() > 3 &&
						canClickSignUp){
					button.setText("Wait...");
					canClickSignIn = false;
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							System.out.println("Login Thread Run");
							main.client.sendData(new DataLogin(idField.getText(), pwField.getText()));
							DataLogin data = (DataLogin) main.client.receiveData();
							if (data.getBool()){
								System.out.println("return true");
								main.loginComplete(idField.getText());
							}
							else {
								System.out.println("return false");
							}
							button.setText(strSignIn);
							canClickSignIn = true;
						}
					});
					thread.start();
					System.out.println("Thread 가 정상적으로 작동할까요?");
					System.out.println("그런것 같습니다!");
				}
				
			}
			else if (button.getText().equals(strSignUp)) {
				if (canClickSignUp &&
						idField.getText().length() > 3 &&
						pwField.getText().length() > 3 &&
						canClickSignIn){
					button.setText("Wait...");
					canClickSignUp = false;
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							System.out.println("SignUp Thread Run");
							main.client.sendData(new DataRegister(idField.getText(), pwField.getText()));
							DataRegister data = (DataRegister) main.client.receiveData();
							if (data.getBool()){
								System.out.println("return true");
							}
							else {
								System.out.println("return false");
							}
							button.setText(strSignUp);
							canClickSignUp = true;
						}
					});
					thread.start();
					System.out.println("Thread 가 정상적으로 작동할까요?");
					System.out.println("그런것 같습니다!");
				}
			}
		}
		
	}
	
	
	

}
