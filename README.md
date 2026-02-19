# Expense Tracker CLI

A lightweight command-line expense tracker built in **Java** using **Picocli** and **OpenCSV**.  
The application allows managing personal expenses with persistent CSV storage.

---

## Features
- Add, update, and delete expenses
- List all expenses
- View total and monthly summaries
- Expense categories support
- Monthly budget with warning on exceed
- CSV-based persistence

---

## Data Format
Expenses are stored in `data.csv`:

```csv
id,date,description,amount,category
```
On startup, the CSV file is loaded into memory.
After command execution, the file is rewritten with updated data.

Usage

All commands and options are available via:
```bash
expense-tracker --help
```
Running
Windows (BAT launcher)
```bash
.\expense-tracker <command> [options]
```
