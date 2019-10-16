/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icalc;
import functions.*;
/**
 *
 * @author dstudent
 */
public class Calculator implements ICalc {

    @Override
    @SuppressWarnings("empty-statement")
    public double calculation(String str) {
        //pattern a dzialanie b
        double a,b;
        int pos = str.indexOf('+');
        if(pos == -1)
            pos = str.indexOf('-');
        if(pos == -1)
            pos = str.indexOf('/');
        if(pos == -1)
            pos = str.indexOf('*');
        if(pos != -1 )
        {
            a=Integer.parseInt(str.substring(0,pos));
            b=Integer.parseInt(str.substring(pos+1,str.length()));
            switch(str.substring(pos,pos+1))
            {
                case "+":
                    return a+b;
                case "-":
                    return a-b;
                case "/":
                    if(b==0)
                        return 0;
                    return a/b;
                case "*":
                    return a*b;
            }
        }
        
        //pattern func(a)
        
        String[] exps = {"sqrt("};
        Function[] exps_func = {};
        
        return 0;
    }
    
}


// sqrt(5) 
// sqrt(5+2)
// 5+2
