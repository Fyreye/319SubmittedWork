package portfolio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RunMe
{

	static ConnectWindow CW;
	static boolean boo = true;

	/**
	 * Main runnable to generate the necessary windows for execution of game
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{

		JFrame j = conFrame();
		j.setVisible(true);

		while (boo)
		{
			Thread.sleep(5000);
		}

		int i = -1;
		CW.sendData(i, CW.usr);
		while (CW.listner.inputs.isEmpty())
		{
			Thread.sleep(500);
		}
		String data = CW.listner.inputs.remove(0);

		String x = "X", o = "O";
		MainGameWindow gw = new MainGameWindow(data.substring(0, data.indexOf(", ")), (CW.cli < CW.srv) ? x : o,
				(CW.cli > CW.srv) ? x : o, (CW.cli < CW.srv), CW);
		j.dispose();
		CW.parser(gw);

	}

	/**
	 * Connection frame to gather data from user.
	 * 
	 * @return returns the constructed frame
	 */
	public static JFrame conFrame()
	{
		JFrame f = new JFrame("TTT Connect!");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(370, 220);

		JPanel cPane = new JPanel();
		f.setContentPane(cPane);

		JLabel nameLabel = new JLabel("Name");
		JLabel ipLabel = new JLabel("IP Address");
		JLabel cliLabel = new JLabel("My Port");
		JLabel servLabel = new JLabel("Their Port");
		JTextField name = new JTextField("", 10);
		JTextField host = new JTextField("localhost", 10);
		JTextField portC = new JTextField("", 10);
		JTextField portH = new JTextField("", 10);

		cPane.add(nameLabel);
		cPane.add(name);
		cPane.add(ipLabel);
		cPane.add(host);
		cPane.add(cliLabel);
		cPane.add(portC);
		cPane.add(servLabel);
		cPane.add(portH);

		JButton NameButton = new JButton("Ready");
		NameButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String user = name.getText();
				String ip = host.getText();
				int c = Integer.parseInt(portC.getText());
				int s = Integer.parseInt(portH.getText());
				try
				{
					CW = new ConnectWindow(s, c, ip, user);
				}
				catch (Exception ex)
				{
				}
			}
		});
		cPane.add(NameButton);

		JButton ConButton = new JButton("Join");
		ConButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CW.Connect();
				boo = false;
			}
		});
		cPane.add(ConButton);

		return f;
	}

}
