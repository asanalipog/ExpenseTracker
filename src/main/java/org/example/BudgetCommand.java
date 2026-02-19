package org.example;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
@Command(name = "budget", description = "budget --amount []")
public class BudgetCommand implements Runnable {

    @Option(names = "--amount", required = true)
    int amount;
    public void run() {
        if (amount < 0) {
            System.err.println("Amount must be positive.");
        }
        else{
            Main.budget = amount;
        }
    }
}
