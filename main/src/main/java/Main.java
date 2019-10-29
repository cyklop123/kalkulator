/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import icalc.*;

public class Main {

    public static void main(String[] args) {
        //pobieranie danych
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        //wyliczanie
        ICalc calc = new Calculator();
        System.out.println(calc.calculation(str));
    }
    
}
