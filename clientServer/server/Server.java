package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	public static void main(String[] args) throws IOException {
		// SERVIDOR RODANDO NA PORTA 1234
		int port = 12345;
		ServerSocket ss = new ServerSocket(port);
		System.out.println("Server rodando na porta = " + port);
		
		// EXECUTANDO LOOP INFINITO QUE RECEBERÁ AS REQUISIÇÕES DO CLIENTE
		while (true) {
			Socket s = null;

			try {
				// oBJETO DE SOQUETE QUE TRATARÁ AS SOLICITAÇÕES DE CLIENTES RECEBIDAS
				s = ss.accept();
				System.out.println("======================================");
				System.out.println("Cliente Aceito");
				System.out.println("HOSTNAME = " + s.getInetAddress().getHostName());
				System.out.println("HOST ADDRESS = " + s.getInetAddress().getHostAddress());
				System.out.println("PORTA LOCAL = " + s.getLocalPort());
				System.out.println("PORTA DE CONEXAO = " + s.getPort());
				System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
				
				// OBTENDO FLUXO DE ENTRADA E SAÍDA
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				System.out.println("Assigning new thread for this client");

				// CRIA UM NOVO OBJETO THREAD
				Thread t = new ClientHandler(s, dis, dos);

				// CHAMA O MÉTODO START
				t.start();

			} catch (Exception e) {
				s.close();
				e.printStackTrace();
			}
		}
	}
}


class ClientHandler extends Thread {
	SimpleDateFormat fordate = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat fortDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	static PriorityQueue<Integer> pqueue = new PriorityQueue<Integer>();
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;

	// CONSTRUTOR
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		String received;
		String name;
		int priority;
		int service;
		String toreturn;

		while (true) {
			try {

				// PERGUNTAR AO USUÁRIO O QUE ELE QUER
				dos.writeUTF("A: para adiconar requisiçoes\nB: para atender requisições \nE: para sair ");

				// RECEBENDO RESPOSTA DO CLIENTE
				received = dis.readUTF().toLowerCase();
				
				
				
				
			
					//if (received.equals("e")) {
						// System.out.println("Client " + this.s + " sends exit...");
						//System.out.println("Closing this connection.");
						//this.s.close();
						//System.out.println("Connection closed");
						//System.out.println("======================================");
						//System.out.println();
						//break;
					//}

					// CRIANDO OBJETO Date
					Date date = new Date();

					// GRAVA O FLUXO DE SAÍDA DE ACORDO COM RESPOSTA DO CLIENTE
					// CASO SEJA A (MAÍSCULO OU MINUSCULO) O SERVIDOR IRÁ ADICIONAR CLIENTES
					if (received.equals("a")) {
						dos.write(1);
						dos.writeUTF("Quantidade de requisções: ");
						int num = dis.read();
						// É CRIADO UM FOR PARA ENFILEIRAR TODOS OS CLIENTES QUE USUARÃO O SERVIDOR,
						// SEMELHANTE A
						// UM PROCESSO DE REQUISIÇÃO, FOI ESCOLHIDO COLCOAR O FOR, PORQUE ASSIM UMA
						// QUANTIDADE SUPERIOR A 1,
						// PODERIA SER DIGITADA SEQUENCIALMENTE, EM VEZ DE IR ADICIONANDO 1 CLIENTE POR
						// VEZ, UM PROCESSO
						// SEMELHANTE AO QUE OCORRE EM UM PROXY ONDE VÁRIAS REQUISIÇOES SÃO FEITAS EM
						// CURTOS PERÍODOS, E O
						// PROXY PROCESSA ESSAS INFORMAÇÕES ENFILEIRANDO-AS DE ACORDO COM A PRIORIDADE E
						// DEPOIS DESENFILEI-
						// RANDO DE ACORDO COM A PRIORIDADE RETORNANDO AO CLIENTE A REQUISIÇÃO
						// SOLICITADA.

						for (int i = 1; i <= num; i++) {
							dos.writeUTF("Nome do Cliente: ");
							dos.writeUTF("Prioridade: ");
							dos.writeUTF("Serviço: ");

							name = dis.readUTF();
							priority = dis.read();
							service = dis.read();

							// CRIA O OBJETO E O ENFILEIRA DE ACORDO COM A PRIORIDADE INFORMADA
							Message msg = new Message(name, priority, service);
							pqueue.enqueue(msg, msg.getPriority());
						}

					} else if (received.equals("b")) {
						// CASO A RESPOSTA SEJA B (MAÍSCULO OU MINUSCULO) A FILA SERÁ ESVAZIADA DE
						// ACORDO COM A PRIORIDADE
						dos.write(2);
						dos.write(pqueue.size());
						if (pqueue.size() > 0) {
							while (pqueue.eVazia() == false) {
								Message msn = (Message) pqueue.dequeueO();
								switch (msn.getService()) {
								case 1:
									System.out.println(msn.toString());
									System.out.println("Service resquest: data");
									dos.writeUTF(msn.toString());
									toreturn = fordate.format(date);
									dos.writeUTF("Retornando Data: " + toreturn);
									break;

								case 2:
									// dos.write(2);
									System.out.println(msn.toString());
									System.out.println("Service resquest: hora");
									dos.writeUTF(msn.toString());
									toreturn = fortime.format(date);
									dos.writeUTF("Retornando Hora: " + toreturn);
									break;

								case 3:
									// dos.write(2);
									System.out.println(msn.toString());
									System.out.println("Service resquest: data e hora");
									dos.writeUTF(msn.toString());
									toreturn = fortDate.format(date);
									dos.writeUTF("Retornando Date e Hora: " + toreturn);
									break;
								case 4:
									// dos.write(2);
									System.out.println(msn.toString());
									System.out.println("Service resquest: impressão");
									dos.writeUTF(msn.toString());
									toreturn = "Imprimindo...";
									dos.writeUTF(toreturn);
									break;
								case 5:
									// dos.write(2);
									System.out.println(msn.toString());
									System.out.println("Service resquest: banco de dados");
									dos.writeUTF(msn.toString());
									toreturn = "Acessando banco de Dados";
									dos.writeUTF(toreturn);
									break;

								default:
									// dos.write(2);
									dos.writeUTF(msn.toString());
									toreturn = "Serviço requisitado indiponível neste servidor";
									dos.writeUTF(toreturn);
									break;
								}
							}
							
						}	
					} else if (received.equals("e")) {
						dos.write(3);
						// System.out.println("Client " + this.s + " sends exit...");
						System.out.println("Closing this connection.");
						this.s.close();
						System.out.println("Connection closed");
						System.out.println("======================================");
						System.out.println();
						break;
					} else {
						dos.write(4);
						dos.writeUTF("Entrada Inválida");
					}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			// closing resources
			this.dis.close();
			this.dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}