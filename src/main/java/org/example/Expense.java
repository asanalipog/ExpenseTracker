package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

//    LocalDate today = LocalDate.now();
public class Expense {
    //TODO create a hashmap that could store overall expenses for a certain "description"
    @CsvBindByName(column = "id")
    public int id;
    @CsvBindByName(column = "description")
    public String description;
    @CsvBindByName(column = "amount")
    public int amount;
    @CsvBindByName(column = "date")
    @CsvDate("yyyy-MM-dd")
    public LocalDate time;
    @CsvBindByName(column = "category")
    public Category category;//TODO make a use of it
    public Expense() {
        // required by OpenCSV for reflection-based instantiation
    }

    public Expense(String description, int amount) {
        this.description = description;
        this.amount = amount;
        this.time = LocalDate.now();
    }

    public Expense(String description, int amount, String category) {
        this.description = description;
        this.amount = amount;
        this.time = LocalDate.now();
        this.category = Category.valueOf(category.toUpperCase());
    }



}
