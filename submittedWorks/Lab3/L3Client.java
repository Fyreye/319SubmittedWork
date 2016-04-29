package Lab3;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class L3Client
{

	Socket clientSocket = null, peer = null;
	PrintWriter out = null;
	Scanner in = null;
	String hostIP, userName;
	int clientPort = 5556, serverPort = 5555;
	static ChatWindow chatWindow;
	static boolean flag = true;
	static L3Client l3Client = null;

	public static void main(String[] args)
	{
		JFrame j = landingFrame();
		while (flag)
		{
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		chatWindow = new ChatWindow("LocalHost", l3Client);

		j.dispose();

		ListenThread l = new ListenThread(l3Client.userName + "LT", l3Client.in);
		l.start();
		ParseThread p = new ParseThread(l3Client.userName + "PT", l, chatWindow);
		p.start();
	}

	public static JFrame landingFrame()
	{
		JFrame f = new JFrame("Connect!");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(370, 220);

		JPanel cPane = new JPanel();

		JLabel Cli = new JLabel("Client");
		Cli.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		cPane.add(Cli);
		JLabel Name = new JLabel("Enter your name:");
		Name.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		cPane.add(Name);

		JTextField name = new JTextField("", 10);
		cPane.add(name);

		JButton NameButton = new JButton("Join");
		NameButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String user = name.getText();
				try
				{
					l3Client = new L3Client(5555, "localhost", user);
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
				flag = false;
			}
		});
		cPane.add(NameButton);

		f.add(cPane);
		f.setVisible(true);
		return f;
	}

	public L3Client(int server, String ip, String user)
	{

		hostIP = ip;
		serverPort = server;
		userName = user;
		Socket s;
		try
		{
			s = new Socket("localhost", 5555);
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String sendData(String message)
	{
		try
		{
			out.println(userName + ":> " + message);
			out.flush();
		}
		catch (Exception e)
		{
			return "fail";
		}
		System.out.println(message + " to " + serverPort);
		return message;
	}
}


class ListenThread extends Thread
{

	Scanner in = null;
	ArrayList<String> inputs = new ArrayList<String>();

	public ListenThread(String name, Scanner input)
	{
		super(name);
		in = input;
	}

	public void run()
	{
		while (true)
		{
			if (in.hasNextLine())
			{
				String data = in.nextLine();
				if (!(data == null))
					inputs.add(data);
			}
		}
	}
}


class ParseThread extends Thread
{

	ChatWindow chat;
	ListenThread list;

	public ParseThread(String name, ListenThread listner, ChatWindow window)
	{
		super(name);
		list = listner;
		chat = window;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (!list.inputs.isEmpty())
			{
				String data = list.inputs.remove(0);
				chat.displayReceivedText(data);
			}
		}
	}
}
