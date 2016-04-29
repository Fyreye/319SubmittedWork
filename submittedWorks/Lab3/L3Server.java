package Lab3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class L3Server
{

	ServerSocket serverSoc = null;
	PrintWriter out = null;
	Scanner in = null;
	String hst, usr;
	int clientPort = 5556, serverPort = 5555;
	ArrayList<Socket> clientList = new ArrayList<Socket>();
	ArrayList<Handler> handlerList = new ArrayList<Handler>();
	ArrayList<String> messageList = new ArrayList<String>();

	public static void main(String[] args)
	{
		L3Server server = new L3Server(5555);

		Scanner console = new Scanner(System.in);
		String serverMsg;
		while (true)
		{
			if (console.hasNext())
			{
				serverMsg = console.nextLine();
				server.messageList.add("-1, SERVER:>" + serverMsg);
				if (serverMsg.equals("kill"))
					break;
			}
		}
		console.close();
	}

	public L3Server(int server)
	{
		try
		{
			serverSoc = new ServerSocket(server);
		}
		catch (IOException io)
		{
			System.out.println("Could not listen on port" + server);
			System.exit(-1);
		}
		serverPort = server;
		Message m = new Message("M1", this, handlerList);
		m.start();
		System.out.println("ready");
		ConsoleThread cmd = new ConsoleThread("cmd", this);
		cmd.start();
		while (true)
		{
			Socket clientSocket = null;
			try
			{
				clientSocket = serverSoc.accept();
				System.out.println("new Client connected");
				clientList.add(clientSocket);
				handlerList.add(new Handler("" + clientList.size(), clientSocket, this, clientList.size()));
				handlerList.get(handlerList.size() - 1).start();
				Listner l = new Listner("" + clientList.size(), handlerList.get(handlerList.size() - 1));
				l.start();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}
}

class ConsoleThread extends Thread
{
	
	L3Server s;
	
	public ConsoleThread(String name, L3Server server)
	{
		super(name);
		s = server;
	}
	
	public void run()
	{
		Scanner console = new Scanner(System.in);
		String serverMsg;
		while(true){
			if(console.hasNext())
			{
				serverMsg = console.nextLine();
				s.messageList.add("-1, SERVER:>" + serverMsg);
			}
		}
	}
}
class Message extends Thread
{

	L3Server server;
	ArrayList<Handler> messageHandlerList;

	public Message(String name, L3Server server, ArrayList<Handler> h)
	{
		messageHandlerList = h;
		this.server = server;
		System.out.println("message up");
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
			while (!server.messageList.isEmpty())
			{
				String msg = server.messageList.remove(0);

				System.out.println("msg get: " + msg.substring(msg.indexOf(',') + 1));

				for (Handler h : messageHandlerList)
				{
					if (!msg.substring(0, msg.indexOf(',')).equals(h.getName()))
					{
						h.outMsg.add(msg.substring(msg.indexOf(',') + 1));
					}
				}
			}
		}
	}
}


class Listner extends Thread
{

	Handler handle;

	public Listner(String name, Handler h)
	{
		super(name);
		handle = h;
	}

	public void run()
	{
		while (true)
		{
			if (handle.in.hasNextLine())
			{
				System.out.println("new msg get (Handler)");
				handle.s.messageList.add(handle.num + ", " + handle.in.nextLine());
			}
		}
	}
}


class Handler extends Thread
{

	Socket c;
	L3Server s;
	Scanner in;
	ArrayList<String> outMsg = new ArrayList<String>();
	PrintWriter out;
	int num;

	public Handler(String name, Socket client, L3Server server, int id)
	{
		super(name);
		c = client;
		s = server;

		System.out.println("handler for " + client.getPort());

		try
		{
			in = new Scanner(c.getInputStream());
			out = new PrintWriter(c.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		num = id;
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
			if (!outMsg.isEmpty())
			{
				System.out.println("sending a msg: " + outMsg.get(0));

				out.println(outMsg.remove(0));
				out.flush();
			}
		}
	}
}
