package org.example;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.time.LocalDate;

@Command(name = "summary", description = "summary --month [] --category []")
public class SummaryCommand implements Runnable{

    @Option(names="--month", defaultValue = "12")
    int period;
    private LocalDate now = LocalDate.now();

    @Option(names="--category")
    Category category;

    int counter = 0;
    public void run(){
        LocalDate startTime = now.minusMonths(period);
        for (Expense expense : Main.expenses){
            if (expense.time.isAfter(startTime)) counter = counter + expense.amount;
        }
        System.out.println("Total expenses are: $" + counter);
    }
}
