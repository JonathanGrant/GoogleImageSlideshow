import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Runner {
	public static void main(String[] args){
		String imgToSearch = JOptionPane.showInputDialog("Googlerz");
		
	}
}

class draw extends JFrame {
	public draw(String imgName){
		super(imgName + "");
		setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-50), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-150));
		setVisible(true);
	}
}
