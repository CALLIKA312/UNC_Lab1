import Controller.MainController;
import Utils.FlightParser;
import com.sun.glass.ui.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private static FlightParser flightParser;

    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter(System.out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        writer.println("Input filepath");
        String filePath = reader.readLine();
        // flightParser.fileParse(filePath);
    }
}
