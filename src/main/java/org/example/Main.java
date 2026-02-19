package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

// indexOf(Object O) -> to find index of object
/*
//1. add description amount
//2. update expenses by the index
//3. delete expenses by the index
//4. view all expenses - list
5. summary of expenses
6. summary by the specific time of this calendar year?
//7. write and read csv file after operations
8. care about the budget
 */

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import com.opencsv.bean.CsvToBeanBuilder;

import com.opencsv.CSVReader;
import java.io.FileReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Command(
        name = "expense",
        mixinStandardHelpOptions = true,
        subcommands = {
                AddCommand.class,
                ListCommand.class,
                DeleteCommand.class,
                BudgetCommand.class,
                UpdateCommand.class,
                SummaryCommand.class
        }
)
public class Main implements Runnable {
    static int total_expenses = 0;
    static HashMap<String, Integer> list_expenses = new HashMap<>();
    static ArrayList<Expense> expenses = new ArrayList<>();
    static int budget = Integer.MAX_VALUE;
    static String filePath = "C:\\Users\\askar\\Desktop\\projects\\ExpenseTracker\\data.csv";
    public void run() {
        CommandLine.usage(this, System.out);
    }

    public static void main(String[] args) {
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            try (FileReader fr = new FileReader(filePath)) {
                expenses = (ArrayList<Expense>) new CsvToBeanBuilder<Expense>(fr)
                        .withType(Expense.class)
                        .build()
                        .parse();
            } catch (IOException e) {
                System.err.println("Error opening file: " + e.getMessage());
                expenses = new ArrayList<>();
            }
        } else {
            expenses = new ArrayList<>();
        }

        new CommandLine(new Main()).execute(args);

        try (FileWriter fw = new FileWriter(filePath);
             CSVWriter writer = new CSVWriter(fw)) {

            String[] header = {"id", "date", "description", "amount", "category"};
            writer.writeNext(header);

            for (Expense e : expenses) {
                filler(e); // Just updates valus for every description
                writer.writeNext(new String[]{
                        String.valueOf(e.id),
                        e.time.toString(),          // or format it
                        e.description,
                        String.valueOf(e.amount),
                        e.category == null ? "" : e.category.name()
                });
            }
            if (budget < total_expenses) {

                System.out.println("You are exceeding your budget.");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }




    static void assigner(ArrayList<Expense> expenses){
        for  (Expense expense : expenses){
            expense.id =  expenses.indexOf(expense) + 1;
        }
    }
    static void filler(Expense expense) {
        String description = expense.description;
        if (list_expenses.containsKey(description)) list_expenses.put(description, list_expenses.get(description) + expense.amount);
        else list_expenses.put(description,expense.amount);
        LocalDate now = LocalDate.now();
        LocalDate startTime = now.minusMonths(1);
        if (expense.time.isAfter(startTime)) total_expenses = total_expenses + expense.amount;
    }
    static void display() {
        //TODO change that part of displaying
        for (Map.Entry<String, Integer> entry : list_expenses.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

