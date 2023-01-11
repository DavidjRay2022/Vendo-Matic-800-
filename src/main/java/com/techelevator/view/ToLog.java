package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToLog {
    static  File logFile;
    static  PrintWriter logOutput;

    //Date time formatter
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyy hh:mm:ss a");
    static LocalDateTime now = LocalDateTime.now();



    public static void log(String message){

        try{

            if (logFile == null ){
                logFile = new File("logs/VendingMachine.log");
                logOutput = new PrintWriter(new FileOutputStream(logFile,true),true); //appends with FileOutputStream true instead of overwriting
            }

            logOutput.println(dtf.format(now) + " " + message);

        } catch (FileNotFoundException e){
            throw new ToLogException(e);
        }


    }
}
