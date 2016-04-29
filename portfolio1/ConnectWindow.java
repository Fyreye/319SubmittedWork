package portfolio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class ConnectWindow
{

	ParseThread parse;
	ServerSocket serverSoc = null;
	Socket clientSoc = null, peer = null;
	PrintWriter out = null;
	Scanner in = null;
	String hst, usr;
	int cli, srv;
	ServerThread listner = null;

	/**
	 * 
	 * Handles communication between clients
	 * 
	 * @param server - server port num
	 * @param client - client port num
	 * @param host - ip to connect to
	 * @param user - user's name for stylistic use later
	 */
	public ConnectWindow(int server, int client, String host, String user)
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
		hst = host;
		cli = client;
		srv = server;
		usr = user;
		System.out.println("created");
	}

	/**
	 * launches a thread to interpret data being collected
	 * 
	 * @param mgw gui to pass data to
	 */
	public void parser(MainGameWindow mgw)
	{
		parse = new ParseThread(hst + "Parse", listner, mgw);
		parse.start();
	}

	/**
	 * connects this client to server on other end
	 * 
	 * @return true if the connection was successful false if connection failed
	 */
	boolean Connect()
	{
		try
		{
			try
			{
				clientSoc = new Socket(hst, cli);
				out = new PrintWriter(clientSoc.getOutputStream());
			}
			catch (IOException e)
			{
				System.out.println("Connection error: 42");
			}
			peer = serverSoc.accept();
			in = new Scanner(peer.getInputStream());
		}
		catch (IOException io)
		{
			return false;
		}

		listner = new ServerThread(hst, in, this);
		listner.start();
		return true;
	}

	/**
	 * Sends data to the other client in the form of string formated
	 * "button, message"
	 * 
	 * @param coord - the id of the selected button 0-8 -1 for no move
	 * @param mesage - for integrated message client and to pass the user name
	 *            at creation of mainGameWindow
	 * @return String containing message if data was sent correctly fail if an error occurred
	 */
	String sendData(int coord, String mesage)
	{
		String outMes = "fail";
		try
		{
			outMes = mesage + ", " + coord;
			out.println(outMes);
			out.flush();
		}
		catch (Exception e)
		{
			return "fail";
		}

		return outMes;
	}
}


/**
 * Back end thread to handle communication.
 * 
 * @author Samuel
 *
 */
class ServerThread extends Thread
{

	Scanner in = null;
	ArrayList<String> inputs = new ArrayList<String>();

	public ServerThread(String name, Scanner input, ConnectWindow cw)
	{
		super(name);
		in = input;
	}

	public void run()
	{
		while (true)
		{
			String data = in.nextLine();
			inputs.add(data);
		}
	}

}


class ParseThread extends Thread
{

	MainGameWindow mgw;
	ServerThread list;

	public ParseThread(String name, ServerThread listner, MainGameWindow window)
	{
		super(name);
		list = listner;
		mgw = window;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
			}

			if (!list.inputs.isEmpty())
			{

				String data = list.inputs.remove(0);

				mgw.displayReceivedText(data.substring(0, data.indexOf(", ")));
				if (!data.contains("-1"))
				{
					mgw.markBoard(Integer.parseInt(data.substring(data.lastIndexOf(' ') + 1)));
				}
			}
		}
	}

}
