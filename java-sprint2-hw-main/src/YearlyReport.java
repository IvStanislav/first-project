import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;



public class YearlyReport {
    static String numberYear;
    static ArrayList<YearlyReportRecord> expense = new ArrayList<>();
    static ArrayList<YearlyReportRecord> income = new ArrayList<>();

    public static void addReportYear(String year, String patch) {
        numberYear = year;
        String text = readFileContents(patch);
        String[] lines = text.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            YearlyReportRecord yearlyReportRecord = new YearlyReportRecord(month, amount, isExpense);
            if (isExpense) {
                expense.add(yearlyReportRecord);
            } else {
                income.add(yearlyReportRecord);
            }

        }
    }
    static void printYearReport() {
        if (numberYear != null) {
            System.out.println("Прибыль по месяцам " + numberYear + " года");

            int outgo = 0;
            int expens = 0;
            int difference = 0;

            for (YearlyReportRecord inc : income) {
                outgo = inc.expenses;

                for (YearlyReportRecord exp : expense) {
                    expens = exp.expenses;

                    if (inc.month == exp.month) {
                        difference = outgo - expens;
                    }
                }

                System.out.println(inc.month + "й месяц - " + difference);
            }
            middleExpense();
            middleIncom();
        } else {
            System.out.println(" Сначала считайте годовой отчёт.");
        }
    }


    static void middleExpense() {
        int expens = 0;
        int middle = 0;
        for (YearlyReportRecord car : expense) {
            expens += car.expenses;
        }
        middle = expens / expense.size();
        System.out.println("Средний расход - " + middle);
    }

    static void middleIncom() {
        int income = 0;
        int middle = 0;
        for (YearlyReportRecord com : YearlyReport.income) {
            income += com.expenses;
        }
        middle = income / YearlyReport.income.size();
        System.out.println("Средний доход - " + middle);
    }


    static String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
    }
