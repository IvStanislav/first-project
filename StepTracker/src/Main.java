import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();

        while (true) {
            printMenu();
            System.out.print("Введите команду для продолжения: ");
            int command = scanner.nextInt();

            if (command == 1) {
                System.out.print("Введите номер месяца (нумерация начинается с 0): ");
                int month = scanner.nextInt();
                System.out.print("Введите номер дня (нумерация начинается с 0): ");
                int day = scanner.nextInt();
                stepTracker.saveSteps(month, day);

            } else if (command == 2) {
                System.out.print("Введите номер месяца (нумерация начинается с 0): ");
                int month = scanner.nextInt();
                StepTracker.printMonthInfo(month);

            } else if (command == 3) {
                stepTracker.changeObjectSteps();

            } else if (command == 0) {
                System.out.println("Программа завершена");
                return;

            } else {
                System.out.println("Извините, такой команды не существует.");
            }
            }
        }
    static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за день");
        System.out.println("2 - Просмотреть статистику за месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выход");
    }
}
