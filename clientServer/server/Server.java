package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import client.Message;


// SERVIDOR NTP (NETWORK TIME PROTOCOL)
public class Server extends Thread  
{ 	
	
	SimpleDateFormat fordate = new SimpleDateFormat("dd/MM/yyyy"); 
	SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss"); 
    SimpleDateFormat fortimeData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ; 
	private static ServerSocket ss;
	private Socket s;
	private final static int PORT = 3128;
	
	
	public Server(Socket s) {
		this.s = s;
	}
	
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			String toreturn;
			Object x = null;

			
			do {
				x = in.readObject();
				System.out.println(x.toString());
				Date date = new Date();
				
				if (x instanceof Message) {
					
					try {
						out.writeObject(((Message) x));
						switch (((Message) x).getService()) {
						case 1:
							System.out.println("Serviço requisitado Data");
							toreturn = fordate.format(date);
							out.writeUTF("Serviço de Data requisitado");
							out.writeObject("Data: " + toreturn);
							break;
						case 2:
							System.out.println("Serviço requisitado Hora");
							toreturn = fortime.format(date);
							out.writeUTF("Serviço de Hora requisitado");
							out.writeObject("Hora: " + toreturn);
							break;
						case 3:
							System.out.println("Serviço Requisitado Data e Hora");
							toreturn = fortimeData.format(date);
							out.writeUTF("Serviço de Data e Hora requisitado");
							out.writeObject("Data e Hora: " + toreturn);
							break;
						default:
							out.writeUTF("Serviço Indiponível");
							out.writeObject("Serviço não listado");
							break;
						}
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} while (!(x instanceof String) && x != "exit");
			
			if (x instanceof String && x != "exit") {
				System.out.println("======================================");
				s.close();
				out.close();
				in.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Server Socket rodando na porta = "
				+ ss.getLocalPort());
		while (true) {
			try {
				Socket conexao = ss.accept();
				System.out.println();
				System.out.println("Cliente Aceito");
				System.out.println("HOSTNAME = " + conexao.getInetAddress().getHostName());
				System.out.println("HOST ADDRESS = " + conexao.getInetAddress().getHostAddress());
				System.out.println("PORTA LOCAL = " + conexao.getLocalPort());
				System.out.println("PORTA DE CONEXAO = " + conexao.getPort());
				System.out.println("======================================");
				new Server(conexao).start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
}