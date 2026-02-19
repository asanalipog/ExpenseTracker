package org.example;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "add", description = "add --dec [] --amount [] --category [] (category is optional)")
public class AddCommand implements Runnable {

    @Option(names = "--desc", required = true)
    String description;

    @Option(names = "--amount", required = true)
    int amount;

    @Option(names="--category", defaultValue="OTHER")
    String category; // optional

    public void run() {
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }
        Expense expense;

        if (category == null) {
            expense = new Expense(description, amount);

        }
        else {
            expense = new Expense(description, amount, category);
        }
        Main.expenses.add(expense);
        expense.id = Main.expenses.indexOf(expense) + 1;
        System.out.println("Added: " + description + " " + amount);
    }
}
