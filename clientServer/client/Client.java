package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		try {
			Scanner scn = new Scanner(System.in);

			// OBTENDO IP LOCAL DO HOST
			InetAddress ip = InetAddress.getByName("localhost");

			// ESTABELECENDO CONEXÃO COM O SERVIDOR NA PORTAA 12345
			Socket s = new Socket(ip, 12345);

			// OBTENDO FLUXO DE ENTRADA E SAÍDA
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			
			/*
			 * NO CLIENTE OCORRERÁ O PROCESSO DE SIMULAÇÃO DE UMA CONEXÃO COM O SERVIDOR CONTENDO OS SEGUINTES SERVIÇOS
			 * ABAIXO, INICIALMENTE PENSOU EM ALOCAR A PRIORIDADE UTILIZANDO A MESMA NUMERAÇÃO DO SERVIÇO, PORÉM DEPEN-
			 * DENDO DO USUÁRIO A SUA PRIORIDADE VAI SER MAIOR MESMO COM SERVIÇOS QUE PODEM CONSUMIR MAIS TEMPO PARA SE-
			 * REM PROCESSADOS, UM EXEMPLO É UM USUÁRIO COMUM PEDINDO INFORMAÇÃO DE DATA DO SERVIDOR ENQUANTO OUTRO CLI-
			 * ENTE FAZ A REQUISÇÃO DE ACESSO A UM BD, PORÉM ESSE CLIENTE É O CHEFE DA EMPRESA QUE ESTÁ VERIFICANDO ALGO
			 * NO BD, TEORICAMENTE O CLIENTE IA SER CONCTAR NO SERVIDOR, E DO SERVIDOR IRIA SE INICAR OUTRA CONECXÃO COM
			 * O BD ISSO CERTAMENTE TOMA MAIS TEMPO QUE APENAS RETORNAR A HORA, PORÉM O CLIENTE QUE REQUISITA TEM MAIOR 
			 * PRIORIDADE.
			 * 
			 * ENTÃO NESSA ATIVIDADE A PRIORIDADE SERÁ ATRELADA AO CLIENTE E NÃO SO SERVIÇO. 
			 */
			
			System.out.println("Serviço 1: Data");
			System.out.println("Serviço 2: Hora");
			System.out.println("Serviço 3: Data e Hora");
			System.out.println("Serviço 4: Impressão");
			System.out.println("Serviço 5: Acesso a BD");
			System.out.println("A prioridade é numérica, onde menor o número maior a prioridade, ou seja, prioridade 1"
					+ " é maior que a prioridade 3.");
			System.out.println();
			int err;
			
			// O LOOP PROCESSA E EXECUTA AS TROCAS DE INFORMAAÇÕES COM SERVIDOR 
			while (true) {
				System.out.println(dis.readUTF());
				String tosend = scn.nextLine();
				dos.writeUTF(tosend);
				
			
				

				// CASO O CLIENTE DIGITE exit A CONEXÃO SERÁ FECHADA INTERROMPENDO O LOOP WHILE
				//if (tosend.equals("e")) {
				//	System.out.println();
				//	System.out.println("Closing this connection");
				//	s.close();
				//	System.out.println("Connection closed");
				//	break;
				//}

				// PRINTANDO AS OPERAÇÕES DISPONÍVEIS NO SERVIDOR
				int op = dis.read(); 
				if (op == 1) {
					System.out.println();
					System.out.println(dis.readUTF());
					int send = scn.nextInt();
					dos.write(send);
					for (int i = 0; i < send; i++) {
						System.out.println();
						System.out.print(dis.readUTF());
						String name = scn.next();
						System.out.print(dis.readUTF());
						int priority = scn.nextInt();
						scn.nextLine();
						System.out.print(dis.readUTF());
						int service = scn.nextInt();
						scn.nextLine();

						dos.writeUTF(name);
						dos.write(priority);
						dos.write(service);
					}
					System.out.println();
					System.out.println("Requisições Adicionadas à fila de serviços do servidor");
					System.out.println();

				} else if (op == 2) {
					// RETORNA OS CLIENTES PREFERENCIAS NA FILA DE PRIORIDADE, PRINTANDO A SUA POSIÇÃO NA FILA
					// O NOME DO CLIENTE, A PRIORIDADE E O SERVIÇO REQISITADO
					int len = dis.read();
					if (len > 0) {
						System.out.println("--------------------------------------------------------");
						for (int i = 0; i < len; i++) {
							System.out.println("Atendendo o cliente nº " + (i + 1) + " da fila de serviços");
							System.out.println(dis.readUTF());
							String received = dis.readUTF();
							System.out.println(received);
							System.out.println();
						}
						System.out.println("--------------------------------------------------------");
					} else {
						System.out.println();
						System.out.println("Impossível atender a fila de requisiçoes pois está vazia");
					}
					
					System.out.println();
				} else if (op == 3) {
					System.out.println();
					System.out.println("Closing this connection");
					s.close();
					System.out.println("Connection closed");
					break;					
				} else {
					System.out.println();
					System.out.println(dis.readUTF());
					System.out.println();
				}
				
				}
				


			// closing resources
			scn.close();
			dis.close();
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
