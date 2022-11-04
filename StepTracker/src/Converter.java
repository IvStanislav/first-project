public class Converter {
    static double caloriesInOne = 50;
    static double distanceInOne = 75;
    public static double caloriesBurn(int sumStep) {
        double Calories = StepTracker.sumSteps * caloriesInOne / 1000;
        return Calories;
    }
    public static double distanceLong(int sumStep) {
        double Distance = StepTracker.sumSteps * distanceInOne / 100000;
        return Distance;
    }
}
