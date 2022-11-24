import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();

        while (true) {
            printMenu();
            System.out.print("Введите команду для продолжения: ");
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    for (int i = 1; i < 4; i++) {
                        monthlyReport.addReportMonth(i, "resources/m.20210" + i + ".csv");
                    }
                    break;
                case 2:
                    yearlyReport.addReportYear("2021", "resources/y.2021.csv");
                    break;
                case 3:
                    monthlyReport.verifyReports();
                    break;
                case 4:
                    monthlyReport.printStatic();
                    break;
                case 5:
                    yearlyReport.printYearReport();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Такой команды не существует, повторите ввод.");
            }
        }
    }

        static void printMenu() {
            System.out.println("Что вы хотите сделать? ");
            System.out.println("1 - Считать все месячные отчёты");
            System.out.println("2 - Считать годовой отчёт");
            System.out.println("3 - Сверить отчёты");
            System.out.println("4 - Вывести информацию о всех месячных отчётах");
            System.out.println("5 - Вывести информацию о годовом отчёте");
            System.out.println("0 - Выход");
        }
    }
