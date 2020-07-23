package br.uema.pecs.ed.lista02;

import java.util.Scanner;


import entitiesList3.ListChained;
import entitiesList3.Site;



public class LinkedList3 {

		@SuppressWarnings("resource")
		public static char menu() {
		
			Scanner sc = new Scanner(System.in);
			
			System.out.println("---------------------------------");
			System.out.println("== ESCOLHA UMA OPÇÃO ==");
			System.out.println("---------------------------------");
			System.out.println("== 1. INSERIR NO INÍCIO        ==");
			System.out.println("== 2. INSERIR NO MEIO          ==");
			System.out.println("== 3. INSERIR NO FINAL         ==");
			System.out.println("== 4. LOCALIZAR SITE           ==");
			System.out.println("== 5. EXCLUIR SITE             ==");
			System.out.println("== 6. IMPRIMIR LISTA           ==");
			System.out.println("== 7. TAMANHO DA LISTA         ==");
			System.out.println("== 8. SAIR DO PROGRAMA         ==");
			System.out.println("---------------------------------");
			char msg = sc.next().charAt(0);
			return msg;
			
		}//-----------------------------------------------------------------------------------MENU
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			/*
			 * 
			 * 3 QUESTÃO
			 * 
			 * */
			Scanner ler = new Scanner(System.in);
			
			ListChained list = new ListChained();
			Site s;
			char opcao;
			String site;
			String link;
			
			do {
				
				opcao = menu();
				System.out.println();
				
				switch(opcao) {
				case '1':
					System.out.println("Inserindo no inicio da lista");
					
					System.out.print("Site: ");
					site = ler.next(); 
					System.out.print("Link: ");
					link = ler.next(); 
					
					s = new Site(site, link);
					list.inserirPrimeiro(s);
					break;
					
				
				case '2':
				if (list.getLenght() <= 1) {
					System.out.println("Ainda não é possível adiconar no meio");
					System.out.println("Adicione no inicio ou no final");
				} else {
					System.out.println("Inserindo no meio da lista");
					System.out.print("Site: ");
					site = ler.next();
					System.out.print("Link: ");
					link = ler.next();

					s = new Site(site, link);
					list.inserirMeio(s);

				}
				break;			
					
				case '3':
					System.out.println("Inserindo no final da lista");
					
					System.out.print("Site: ");
					site = ler.next();
					System.out.print("Link: ");
					link = ler.next();

					s = new Site(site, link);
					list.inserirUltimo(s);
					break;
					
			 
				case '4':
					System.out.println("Localizar site");
					if (list.eVazia()) {
						System.out.println("A lista é vazia ");
					} else {
						System.out.print("Localizando site \nDigite o nome: ");
						String nomeb = ler.next();
						if (list.pesquisaNo(nomeb) == null) {
							System.out.println("O site procurado não está na lista");
						} else {
							System.out.println(list.pesquisaNo(nomeb));
						}
						
					}
					break;
					
					
				case '5':
					if(list.eVazia()) {
						System.out.println("Lista vazia");
					} else {
						System.out.print("Digite o nome do site a ser excluida: ");
						String nomee = ler.next();
						if(list.removerNo(nomee)) {
							System.out.println( nomee + " removido com sucesso");
						} else {
							System.out.println("Não foi possível remover " + nomee);
						}
					}
					break;
				case '6':
						System.out.println("Imprimindo lista");
						System.out.println("Lista: " + list.imprimirLista());
						System.out.println();
					break;
					
				case '7':
					System.out.println("A lista contem " + list.getLenght() + " sites");
					break;
					
				case '8':
					System.out.println("Fim do programa");
					break;
				
				default:
					System.out.println("Opção inválida, tente novamente.");
					
					
				}
				
			} while (opcao != '8');
			
			System.exit(0);
			
			ler.close();
			
		}
}
