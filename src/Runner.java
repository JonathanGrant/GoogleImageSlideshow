import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Runner {
	public static void main(String[] args){
		String imgToSearch = JOptionPane.showInputDialog("Googlerz");
		draw drawer = new draw("imgToSearch");
	}
}

class draw extends JFrame {
	String imgToSearch = "";
	Image screenImage;
	JSONArray ja = new JSONArray();
	public draw(String imgName) {
		super(imgName + "");
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150));
		setVisible(true);
		JSONObject json = null;
		URL url;
		try {
			imgToSearch=imgName.replaceAll(" ", "%20");
			url = new URL("https://ajax.googleapis.com/ajax/services/search/images?" +
			        "v=1.0&q="+imgToSearch+"&userip=INSERT-USER-IP");

			URLConnection connection = url.openConnection();

			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				builder.append(line);
			}

			json = new JSONObject(builder.toString());

			//write to array
			ja = json.getJSONArray("results");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gettersz(){
		String urls[] = new String[30];
		JSONObject jo;
		for(int i = 0; i < 30; i++){
			jo = ja.getJSONObject(i);
			urls[i] = jo.getString("unescapedUrl");
			// loading image  
	        try {
				screenImage = Toolkit.getDefaultToolkit().getImage(new URL(urls[i]));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//unescapedUrl
	public void paint(Graphics g){
		if (screenImage != null) // if screenImage is not null (image loaded and ready) 
            g.drawImage(screenImage, // draw it  
            		((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50)) - screenImage.getWidth(this) / 2, // at the center  
            		((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 150)) - screenImage.getHeight(this) / 2, // of screen 
                        this);
	}
}
