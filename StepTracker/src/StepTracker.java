import java.util.Scanner;

public class StepTracker {
    static MonthData[] months;
    static int objectSteps = 10000;
    int monthNum;
    int daysNum;
    static int sumSteps;
    StepTracker() {
        monthNum = 12;
        daysNum= 30;
        months = new MonthData[monthNum];
        for (int i = 0; i < months.length; i++) {
            months[i] = new MonthData();
        }
    }
    void saveSteps(int month, int day) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество шагов: ");
        int steps = scanner.nextInt();
        if (steps < 0) {
            System.out.println("Количество шагов не может быть отрицательным!");
            saveSteps(month, day);
        } else {
            months[month].days[day] = steps;
            System.out.println("Количество шагов записано!");
        }
    }
    static void printMonthInfo(int month) {
    int[] days = months[month].days;
    for (int i = 0; i < days.length; i++){
    System.out.print(i + " день: " + days[i] + ", ");
    }
        System.out.println();
        System.out.println("Общее количество шагов за " + month + " месяц: " + sumStepsInMonth(month));
        System.out.println("Максимальное число шагов за " + month + " месяц: " + maxStepsInMonth(month));
        System.out.println("Среднее количество шагов: " + sumStepsInMonth(month) / days.length);
        System.out.println("Пройденная дистанция: " + Converter.distanceLong(sumSteps) + " километров.");
        System.out.println("Сожжено калорий: " + Converter.caloriesBurn(sumSteps) + " калорий.");
        System.out.println("Лучшая серия: дней - " + maxStreak(month));
        }
    static int sumStepsInMonth(int month) {
        int[] days = months[month].days;
        sumSteps = 0;
        for (int i = 0; i < days.length; i++) {
            sumSteps = sumSteps + days[i];
        }
        return sumSteps;
    }
       static int maxStepsInMonth(int month) {
        int[] days = months[month].days;
        int maxSteps = days[0];
        for (int i = 1; i < days.length; i++) {
            maxSteps = Math.max(maxSteps, days[i]);
        }
        return maxSteps;
    }
    static int maxStreak(int month) {
        int[] days = months[month].days;
        int maxStreak = 0;
        int curStreak = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] <= objectSteps) {
                curStreak = 0;
            }else {
                curStreak++;
            }
            if (curStreak > maxStreak) {
                maxStreak = curStreak;
            }
        }
        return maxStreak;
    }
    void changeObjectSteps() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сейчас целевое количество шагов: " + objectSteps);
        System.out.print("Введите новое целевое значение шагов: ");
        int newObjectStep = scanner.nextInt();
        if (newObjectStep < 0) {
            System.out.println("Количество шагов не может быть отрицательным!");
            changeObjectSteps();
        } else {
            objectSteps = newObjectStep;
            System.out.println("Целевое количество шагов изменено!");
        }
    }
        class MonthData {
            int[] days;

            MonthData() {
                days = new int[daysNum];
                for (int i = 0; i < days.length; i++) {

                }
                }
            }
        }