package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>();
		String a = "exit";
		try {
			
			System.out.print("Números de clientes: ");
			int cli = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Serviços Disponíveis: ");
			System.out.println("1: retorna Data ");
			System.out.println("2: retorna Hora ");
			System.out.println("3: retorna Date e Hora");
			
			
			
			for (int i = 1; i <= cli; i++) {
				System.out.println();
				System.out.println("Cliente " + i);
				System.out.print("Nome do Cliente: ");
				String nameCli = sc.next();
				System.out.print("Priority: ");
				int priority = sc.nextInt();
				sc.nextLine();
				System.out.print("Service: ");
				int service = sc.nextInt();
				sc.nextLine();
				
				// PROXY
				pqueue.enqueue(new Message(nameCli, priority, service), priority);

			}
			
			
			Socket s = new Socket("localhost", 3128);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			for (int i = 0; i < cli; i++) {
				
				System.out.println();
				out.writeObject(pqueue.dequeueO());
				System.out.println(in.readObject());
				System.out.println(in.readUTF());
				System.out.println(in.readObject());
				System.out.println();
				
			}
			out.writeObject(a);
			s.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sc.close();
	}
	
	

}
