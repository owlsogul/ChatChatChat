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
	public JButton loginButton;
	
	public void init(){
		
		this.setLayout(null);
		
		idLabel = new JLabel("ID");
		pwLabel = new JLabel("PW");
		idField = new JTextField();
		pwField = new JTextField();
		loginButton = new JButton("Login");
		
		idLabel.setBounds(10, 10, 40, 30);
		idField.setBounds(50, 10, 150, 30);
		
		pwLabel.setBounds(10, 50, 40, 30);
		pwField.setBounds(50, 50, 150, 30);
		
		loginButton.setBounds(220, 10, 70, 70);
		
		this.add(idLabel);
		this.add(idField);
		this.add(pwField);
		this.add(pwLabel);
		this.add(loginButton);
		this.setVisible(true);
		
	}
	
	
	

}
