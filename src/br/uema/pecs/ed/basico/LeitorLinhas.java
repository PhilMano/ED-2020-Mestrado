/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.pecs.ed.basico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author lccf
 */
public class LeitorLinhas {

    private String[] linhas;
    private int N;

    public LeitorLinhas() {
        linhas = new String[2];
        N = 0;
    }

    private void resize(int max) {
        String[] temp = new String[max];
        for (int i = 0; i < N; i++) {
            temp[i] = linhas[i];
        }
        System.out.println("Tamanho vetor: " + linhas.length + " | " + temp.length);
        linhas = temp;
    }

    //Inserção de um nodo
    public void inserir(String linha) {
        if (N == linhas.length) {
            resize(2 * linhas.length);
        }
        linhas[N++] = linha;
    }
    
    public void imprime() {
        for (int i = 0; i < N; i++) {
            System.out.println(linhas[i]);
        }
        System.out.println("N: " + N + " | Tamanho do vetor: " + linhas.length);
    }
    
    public void sort(){
        Arrays.sort(linhas, 0, N);
    }

    public static void main(String[] args) {
        LeitorLinhas leitor = new LeitorLinhas();
        Scanner ler = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();
        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();;
            while (linha != null) {
                leitor.inserir(linha);
                System.out.printf("%s\n", linha);
                linha = lerArq.readLine();
            }

            arq.close();
        } catch (IOException e) {
        	System.out.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        System.out.println("---------- Linhas do arquivo ----------");
        leitor.imprime();
        leitor.sort();
        leitor.imprime();
    }
}
