package br.uema.pecs.ed.lista03;

import java.util.Scanner;



public class Somatorio {

	public Somatorio() {
	}
	
	public int sum(int num) {
		if (num == 1) {
			return 1;
		}
		return num + sum(num - 1);	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Somatorio soma = new Somatorio();

		String item = "";
		int num;
		
		System.out.println(" * para o fim da execução: ");
		do {
			if (!item.equals("*")) {

				System.out.println();
				System.out.print("Digite o numero para se calcular o somatorio: ");
				num = sc.nextInt();
				sc.nextLine();
				if (num > 0) {
					System.out.println("O somatorio de " + num + " é " + soma.sum(num));
				} else {
					System.out.println("O número digitado deve ser maior que 0");
				}
				System.out.println();
			}

			System.out.print("Digite a opção: ");
			item = sc.next();
			System.out.println();

		} while (!item.equals("*"));

		System.out.print("FIM DA EXECUÇÃO!!!!!!!");
		sc.close();
	}

}
