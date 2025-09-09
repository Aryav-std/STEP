public class Workout {
    String activityName;
    int durationInMinutes;
    int caloriesBurned;

    // Default constructor assigns Walking, 30 mins, 100 calories
    public Workout() {
        activityName = "Walking";
        durationInMinutes = 30;
        caloriesBurned = 100;
    }

    // Constructor with activity name, default duration 30
    public Workout(String activityName) {
        this.activityName = activityName;
        this.durationInMinutes = 30;
        this.caloriesBurned = 150;
    }

    // Constructor with activity and duration, caloriesBurned calculated
    public Workout(String activityName, int durationInMinutes) {
        this.activityName = activityName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = durationInMinutes * 5;
    }

    // Display workout details
    public void displayWorkout() {
        System.out.println("Activity: " + activityName + ", Duration: " + durationInMinutes + " minutes, Calories Burned: " + caloriesBurned);
    }

    public static void main(String[] args) {
        Workout w1 = new Workout();
        Workout w2 = new Workout("Running");
        Workout w3 = new Workout("Cycling", 45);

        w1.displayWorkout();
        w2.displayWorkout();
        w3.displayWorkout();
    }
}
