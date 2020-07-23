package br.uema.pecs.ed.lista03;

import java.util.Scanner;


public class Fibonacci {
	
	public Fibonacci() {
		
	}
	
	public int fibonacci(long num) {
		if (num == 0) {
			return 0;
		} else {
			if (num == 1) {
				return 1;
			} else {
				return fibonacci(num -1) + fibonacci(num - 2);
			}
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Fibonacci fibo = new Fibonacci();
		String item = "";
		long num;
		
		System.out.println(" * para o fim da execução: ");
		do {
			if (!item.equals("*")) {

				System.out.println();
				System.out.print("Digite o numero para se calcular a Sequencia Fibonacci: ");
				num = sc.nextLong();
				if (num > 0) {
					System.out.println("A Seq. Fibonacci de  " + num + " é " + fibo.fibonacci(num));
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
