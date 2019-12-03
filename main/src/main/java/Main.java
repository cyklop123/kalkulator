/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import java.util.Vector;

import icalc.*;

public class Main {

    public static void main(String[] args) {
        try {
            //ładowanie klass z plugins
            File dir = new File("/home/irek2/IdeaProjects/kalkulator/bin/plugins");
            if(dir.exists())
                System.out.println("exist");
            else
                System.out.println("not exist");
            URL loadPath =  dir.toURI().toURL();

            URL[] classUrl = new URL[]{loadPath};

            System.out.println(classUrl.length);

            ClassLoader cl = new URLClassLoader(classUrl);

            Class loaderClass = cl.loadClass("Sqrt");


/*
            System.out.println(
            cl.getClass().getClasses().length
            );
*/

            Field f = ClassLoader.class.getDeclaredField("classes");
            f.setAccessible(true);

            Vector<Class> classes = (Vector<Class>) f.get(cl);

            for(Class c: classes)
                System.out.println(c.getName());

        } catch (MalformedURLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //pobieranie danych
        System.out.println("Podaj wyrażenie matematyczne");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        //wyliczanie
        ICalc calc = new Calculator();
        System.out.println(calc.calculation(str));
    }
    
}
