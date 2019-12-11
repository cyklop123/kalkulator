/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icalc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator implements ICalc {

    @Override
    public double calculation(String str) {
        String onp = ONP(str);
        System.out.println("Develop output: RPN ||| " + onp + " |||");
        return evalONP(onp);
    }

    private double evalONP(String str)
    {
        Stack<Double> stack = new Stack();

        Double number;
        String[] elements = str.split(" ");

        for (String element : elements)
        {
            try
            {
                number = Double.parseDouble(element);
                stack.push(number);
            } catch (NumberFormatException e)
            {
                if (!Character.isLetter(element.charAt(0))) // jeżeli nie jest literką czyli czymś innym czyli operatorem
                {
                    Double temp1 = stack.pop();
                    Double temp2 = stack.pop();
                    switch (element)
                    {
                        case "+":
                            number = temp2 + temp1;
                            break;
                        case "-":
                            number = temp2 - temp1;
                            break;
                        case "*":
                            number = temp2 * temp1;
                            break;
                        case "/":
                            number = temp2 / temp1;
                            break;
                        case "%":
                            number = temp2 % temp1;
                            break;
                        case "^":
                            number = Math.pow(temp2, temp1);
                            break;
                        default:
                            number = null;
                    }
                }
                else
                {
                    number = 0.0;
                    //wyliczanie funkcji
                    Class func;
                    Method param,eval;
                    try{
                        element = element.substring(0,1).toUpperCase() + element.substring(1).toLowerCase();
                        func = Class.forName(element);

                        param = func.getMethod("getParamCount");
                        eval = func.getMethod("eval");

                        int ile = (int) param.invoke(null);

                        ArrayList<Double> params = new ArrayList<>();
                        for(int i=0; i<ile; i++)
                            params.add(stack.pop());

                        number = (double) eval.invoke(params);


                    } catch (ClassNotFoundException ex)
                    {
                        System.out.println("Blad ladowania klasy");
                        ex.printStackTrace();
                    } catch (NoSuchMethodException ex)
                    {
                        System.out.println("Blad wywolania metody");
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex)
                    {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                stack.push(number);
            }
        }
        return stack.pop();
    }

    private String ONP(String str)
    {
        str = str.replace(" ", "");
        Stack<Character> stack = new Stack<>();
        String out = "";

        for (int i = 0; i < str.length(); i++)
        {
            char symbol = str.charAt(i);

            if (Character.isDigit(symbol) || symbol == '.')
            {
                out += symbol;
            }
            else if (Character.isLetter(symbol))
            {
                stack.push(symbol);
            }
            else if (symbol == ',')
            {
                out += " ";
                while (!stack.empty() && stack.peek() != '(')
                    out += " " + stack.pop() + " ";
            }
            else if (symbol == '(')
            {
                stack.push(symbol);
            }
            else if (symbol == ')')
            {
                while (!stack.empty() && stack.peek() != '(')
                {
                    out += " " + stack.pop() + " ";
                }
                stack.pop();//zdejmuje nawias i go wywalam
                //sprawdzam czy na stosie nie ma przypadkiem funkcji
                String fun = "";
                while (!stack.empty() && Character.isLetter(stack.peek()))
                    fun = stack.pop() + fun;
                out += " " + fun + " ";
            }
            else // zostały operatory albo coś innego(niedozwolonego)
            {
                while (!stack.empty() && precedense(symbol) <= precedense(stack.peek()))
                    out += " " + stack.pop() + " ";
                out += " ";
                stack.push(symbol);
            }

        }

        while (!stack.empty())
            out += " " + stack.pop() + " ";

        out = out.replace("  ", " ");
        return out;
    }

    private int precedense(char c)
    {
        switch (c)
        {
            case '(':
                return 0;
            case '+':
            case '-':
            case ')':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}
