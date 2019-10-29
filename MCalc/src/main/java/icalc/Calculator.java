/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icalc;
import functions.*;

public class Calculator implements ICalc {

    @Override
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
            a=Double.parseDouble(str.substring(0,pos));
            b=Double.parseDouble(str.substring(pos+1,str.length()));
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
        Function[] exps_func = {new Sqrt()};

        for(int i=0; i<exps.length; i++)
        {
            pos = str.indexOf(exps[i]);
            if(pos != -1)
            {
                pos = pos+exps[i].length();
                try {
                    return exps_func[i].run(Double.parseDouble(str.substring(pos, (str.indexOf(")")))));
                }
                catch (Exception e){
                    return 0;
                }
            }
        }

        return 0;
    }
    
}


// sqrt(5) 
// sqrt(5+2)
// 5+2