/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.pecs.ed;

/**
 *
 * @author lccf
 */
public class Ed {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calculadora2 calc = new Calculadora2();
        
        double c = calc.potencia(2, 3);
        
        System.out.println(c);
    }
}
