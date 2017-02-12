package cau.wintercamp.chatchat.client;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UILoginPage extends UIPage{
	
	public UILoginPage(UIMain main) {
		super(main);
		init();
	}

	private static final long serialVersionUID = 1L;
	
	public JLabel idLabel, pwLabel;
	public JTextField idField, pwField;
	public JButton signInButton;
	public JButton signUpButton;
	
	public void init(){
		
		System.out.println("initiate Login Page");
		this.setLayout(null);
		
		idLabel = new JLabel("ID");
		pwLabel = new JLabel("PW");
		idField = new JTextField();
		pwField = new JTextField();
		signInButton = new JButton("Sign In");
		signUpButton = new JButton("Sign Up");
		
		idLabel.setBounds(10, 10, 40, 30);
		idField.setBounds(50, 10, 150, 30);
		
		pwLabel.setBounds(10, 50, 40, 30);
		pwField.setBounds(50, 50, 150, 30);
		
		signInButton.setBounds(205, 10, 90, 70);
		signUpButton.setBounds(300, 10, 90, 70);
		
		this.add(idLabel);
		this.add(idField);
		this.add(pwField);
		this.add(pwLabel);
		this.add(signInButton);
		this.add(signUpButton);
		this.setVisible(true);
		
	}
	
	
	

}
