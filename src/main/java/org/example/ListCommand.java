package org.example;


import picocli.CommandLine.Command;

@Command(name = "list", description = "list --category [] (category is optional)")
public class ListCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("# ID  Date       Description  Amount Category");
        for (Expense expense : Main.expenses) {
            System.out.printf("# %-3d %-10s %-12s $%-5d %s\n", expense.id, expense.time, expense.description, expense.amount, expense.category);//TODO complete this assignments
        }

    }
}

