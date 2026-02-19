package org.example;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "delete", description = "delete --id []")
public class DeleteCommand implements Runnable {

    @Option(names = "--id", required = true)
    int id;

    public void run() {
        if (id <= 0 || Main.expenses.size() < id) {
            System.err.println("Invalid id!");
            return;
        }
        Main.expenses.remove(id - 1);
        Main.assigner(Main.expenses);
        System.out.println("Deleting expense " + id);
    }
}
