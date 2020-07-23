package br.uema.pecs.ed.lista03;

import java.util.Scanner;



public class Factorial {
	

	public Factorial() {
	}
	
	public int fatorial(int num) {
		if (num == 0) {
			return 1;
		}
		return num * fatorial(num - 1);

	}

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Factorial fact = new Factorial();
		
		
		String item = "";
		int num;
		
		
		System.out.println(" * para o fim da execução: ");
		do {
			if (!item.equals("*")) {
				
					System.out.println();;
					System.out.print("Digite o numero para se calcular o fatorial: ");
					num = sc.nextInt();
					sc.nextLine();
					if (num > 0) {
						System.out.println("O fatorial de " + num +" é " + fact.fatorial(num));
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
