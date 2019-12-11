
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Scanner;

import icalc.*;

public class Main {

    public static void main(String[] args) {
        //ładowanie pluginów
        HashMap<String,Class> classes = new HashMap<>();

        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(); //sciezka jara
        File dir =  new File( new File(path).getParent() + "/plugins" );

        System.out.println("Loading plugins:");
        try{
            URL url = dir.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);

            File[] files = dir.listFiles((dir1, name) -> name.endsWith(".class"));

            for(File f: files)
            {
                String className = f.getName().substring(0, f.getName().length() - 6);
                Class<?> clazz = cl.loadClass(className);
                classes.put(clazz.getName(),clazz);
                System.out.println(clazz.getName());
            }
        } catch (MalformedURLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        //pobieranie danych
        ICalc calc = new Calculator(classes);

        while(true)
        {
            System.out.println("Podaj wyrażenie matematyczne [0=koniec]");
            Scanner s = new Scanner(System.in);
            String str = s.nextLine();

            if(str.charAt(0) == '0')
                break;

            //wyliczanie
            System.out.println(calc.calculation(str));
        }
    }
    
}
