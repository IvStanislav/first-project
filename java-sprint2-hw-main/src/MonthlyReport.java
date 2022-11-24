import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    static HashMap<Integer, ArrayList<monthlyReportRecord>> reportsMonth;

    public void addReportMonth(Integer month, String patch) {
        reportsMonth = new HashMap<>();
        String text = readFileContentsMonth(patch);
        String[] lines = text.split("\r?\n");

        ArrayList<monthlyReportRecord> list =new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");

            String name = parts[0];
            Boolean isExpense = Boolean.parseBoolean(parts[1]);
            int amount = Integer.parseInt(parts[2]);
            int mexpense = Integer.parseInt(parts[3]);
            if (!reportsMonth.containsKey(name)) {
                monthlyReportRecord monthlyRecord = new monthlyReportRecord(name, isExpense, amount, mexpense);
                list.add(monthlyRecord);
            }
                reportsMonth.put(month, list);
        }
    }
    void printStatic() {
        if (reportsMonth != null) {
            maxIncome();
            maxExpense();
        } else {
           System.out.println("Сначала считайте месячные отчёты.");
        }
    }

    static void maxIncome() {

        int coming;
        String nameOfProduct = "";
        for (Integer month : reportsMonth.keySet()) {
            int maxComing = 0;
            System.out.println("Месяц " + month);
            for (monthlyReportRecord itemNames : reportsMonth.get(month)) {
                if (!itemNames.isExpense) {
                    coming = itemNames.amount * itemNames.mexpense;
                    if (maxComing < coming) {
                        maxComing = coming;
                        nameOfProduct = itemNames.name;
                    }
                }
            }
            System.out.println("Самое прибыльное в этом месяце - " + nameOfProduct + ", продано на сумму " + maxComing);
        }
    }

    static void maxExpense() {
        int expense;
        String nameProduct = "";
        for (Integer month : reportsMonth.keySet()) {
            int maxExp = 0;
            System.out.println("Месяц " + month);
            for( monthlyReportRecord itemsName : reportsMonth.get(month)) {

                if (itemsName.isExpense) {
                    expense = itemsName.amount * itemsName.mexpense;
                    if (maxExp < expense) {
                        maxExp = expense;
                        nameProduct = itemsName.name;
                    }
                }
            }
            System.out.println("Самый большой расход в этом месяце - " + nameProduct + ", продано на сумму " + maxExp);
        }
    }

    static void verifyReports() {
        int income = 0;
        int expenses = 0;
        int different = 0;
        if (reportsMonth != null && YearlyReport.income != null && YearlyReport.expense != null) {
            for (int i = 1; i < 4; i++) {
                income = 0;
                expenses = 0;
                for (monthlyReportRecord report : reportsMonth.get(i)) {
                    if (report.isExpense) {
                        expenses += report.amount * report.mexpense;
                    } else {
                        income += report.amount * report.mexpense;
                    }
                }
                for (YearlyReportRecord incomeYear : YearlyReport.income) {
                    if (incomeYear.month == i && incomeYear.expenses != income) {
                        different++;
                        System.out.println("В месяце " + i + " по доходу обнаружено несоответствие");
                    }
                }
                for (YearlyReportRecord expenseYear : YearlyReport.expense) {
                    if (expenseYear.month == i && expenseYear.expenses != expenses) {
                        different++;
                        System.out.println("В месяце " + i + " по расходу обнаружено несоответствие");

                    }
                }
                if (different == 0) {
                    System.out.println("Операция упешно завершена");
                }
            }

        } else {
            System.out.println("Сначала считайте месячные и годовые отчеты");
        }
    }


    static String readFileContentsMonth(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
}

