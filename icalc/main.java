/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icalc;

/**
 *
 * @author dstudent
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //pobieranie danych
        
        //wyliczanie
        ICalc calc = new Calculator();
        System.out.println(calc.calculation("2.5*2.5"));
    }
    
}
