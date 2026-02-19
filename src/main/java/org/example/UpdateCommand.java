package org.example;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
@Command(name = "update", description = "update --id [] --amount [] --category [] (category is optional)")
public class UpdateCommand implements Runnable  {
    @Option(names = "--id", required = true)
    int id;
    @Option(names = "--amount", required = true)
    int amount;
    @Option(names = "--category", defaultValue="OTHER")
    String category;

    public void run(){
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }
        Main.expenses.get(id-1).amount = amount;
        if (category != null) Main.expenses.get(id-1).category = Category.valueOf(category.toUpperCase());
    }
}
