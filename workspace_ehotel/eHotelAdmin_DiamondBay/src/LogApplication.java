import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class LogApplication{

	private JTextArea textArea;
	int counter = 0;
	
	public LogApplication(String a1, String a2) {
        JFrame f = new JFrame("Log Application");
        f.setSize(600, 750);
        Container content = f.getContentPane();
        content.setBackground(Color.white);
        //FlowLayout layout = new FlowLayout();
        GroupLayout layout = new GroupLayout(f.getContentPane());
        content.setLayout(layout);
        
        textArea = new JTextArea();
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
        textArea.setBounds(5, 5, 500, 600);
        textArea.setVisible(true);
        content.add(textArea);
        
        f.setVisible(true);
        
        Socket sock;
      	String ip = a1;
        int port  = Integer.parseInt(a2);
        try {
			sock = new Socket(ip="172.16.9.202", port=11003);
			InputStream in = sock.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String output;
			int count = 0;
			while((output=br.readLine())!=null){
				if (count>5) {
					if (counter == 30) {
						textArea.setText("");
						textArea.append(output + "\n");
						counter= -1;
					}
					else {
						textArea.append(output + "\n");
						counter++;
					}
				}
				count++;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("0=" + args[0]);
		System.out.println("1=" + args[1]);
        new LogApplication(args[0],args[1]);
	}

}