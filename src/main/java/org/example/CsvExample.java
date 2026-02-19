package org.example;

import com.opencsv.CSVReader;
import java.io.FileReader;

public class CsvExample {

    public static void main(String[] args) throws Exception {

        CSVReader reader = new CSVReader(new FileReader("data.csv"));

        String[] line;
        while ((line = reader.readNext()) != null) {
            for (String value : line) {
                System.out.print(value + " | ");
            }
            System.out.println();
        }

        reader.close();
    }
}
