package icalc;

import java.io.*;
import java.util.Date;

public class Logger {
    private static Logger instance;

    private Logger(){};

    public static Logger getInstance(){
        if(Logger.instance == null)
        {
            return new Logger();
        }
        else
        {
            return instance;
        }
    }

    public void logFromConsole(String stacktrace){
        try{
            Date date = new Date();

            File file = new File("stacktrace.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write('\n');
            br.write(date.toString());
            br.write('\n');
            br.write(stacktrace);

            br.close();
            fr.close();
        }catch (IOException e)
        {
            e.getMessage();
        }

    }

}
