package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class TransactionLog {
        public static void log(String message) {
            File logFile = new File("logs/transactionlog.txt");
            try (PrintWriter historyLog = new PrintWriter(new FileOutputStream(logFile, true))) {
                historyLog.println(message);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);


            }
        }
    }
