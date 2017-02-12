package cau.wintercamp.chatchat.client;

import javax.swing.JLabel;

public class UILoadingPage extends UIPage {

	private static final long serialVersionUID = 1L;

	public UILoadingPage(UIMain main) {
		super(main);
		init();
	}
	
	public JLabel loadingLabel;
	public void init(){
		this.loadingLabel = new JLabel("Loading...");
		this.loadingLabel.setBounds(10 ,10 ,100 ,40);
		this.add(loadingLabel);
		this.setVisible(true);
	}
	
	
	

}
