// Base class
class Weather {
    protected String condition;
    protected double temperature;

    public Weather() {
        this.condition = "Unknown";
        this.temperature = 25.0;
        System.out.println("Weather default constructor called");
    }

    public Weather(String condition, double temperature) {
        this.condition = condition;
        this.temperature = temperature;
        System.out.println("Weather parameterized constructor called");
    }

    public void showWeather() {
        System.out.println("Weather: " + condition + ", Temp: " + temperature + "°C");
    }
}

// Multilevel inheritance: Weather -> Storm -> Thunderstorm
class Storm extends Weather {
    protected double windSpeed;

    public Storm() {
        super();
        this.windSpeed = 50.0;
        System.out.println("Storm default constructor called");
    }

    public Storm(String condition, double temperature, double windSpeed) {
        super(condition, temperature);
        this.windSpeed = windSpeed;
        System.out.println("Storm parameterized constructor called");
    }

    @Override
    public void showWeather() {
        super.showWeather();
        System.out.println("Wind Speed: " + windSpeed + " km/h");
    }

    public void generateStorm() {
        System.out.println("Storm is generating strong winds!");
    }
}

class Thunderstorm extends Storm {
    private boolean lightning;

    public Thunderstorm() {
        super();
        this.lightning = true;
        System.out.println("Thunderstorm default constructor called");
    }

    public Thunderstorm(String condition, double temperature, double windSpeed, boolean lightning) {
        super(condition, temperature, windSpeed);
        this.lightning = lightning;
        System.out.println("Thunderstorm parameterized constructor called");
    }

    @Override
    public void showWeather() {
        super.showWeather();
        System.out.println("Lightning present: " + lightning);
    }

    public void strikeLightning() {
        if (lightning) {
            System.out.println("⚡ Lightning strikes!");
        }
    }
}

// Hierarchical inheritance: Weather -> Sunshine
class Sunshine extends Weather {
    private int uvIndex;

    public Sunshine() {
        super();
        this.uvIndex = 5;
        System.out.println("Sunshine default constructor called");
    }

    public Sunshine(String condition, double temperature, int uvIndex) {
        super(condition, temperature);
        this.uvIndex = uvIndex;
        System.out.println("Sunshine parameterized constructor called");
    }

    @Override
    public void showWeather() {
        super.showWeather();
        System.out.println("UV Index: " + uvIndex);
    }

    public void enjoySun() {
        System.out.println("Enjoying the sunny weather!");
    }
}

// Main class to test
public class WeatherTest {
    public static void main(String[] args) {
        System.out.println("--- Testing Multilevel Inheritance ---");
        Thunderstorm ts = new Thunderstorm("Stormy", 22.5, 80.0, true);
        ts.showWeather();
        ts.strikeLightning();
        ts.generateStorm();

        System.out.println("\n--- Testing Hierarchical Inheritance ---");
        Sunshine sun = new Sunshine("Sunny", 30.0, 7);
        sun.showWeather();
        sun.enjoySun();

        System.out.println("\n--- Polymorphism Test ---");
        Weather[] weatherArray = {ts, sun, new Storm("Windy", 20.0, 60)};
        for (Weather w : weatherArray) {
            w.showWeather(); // demonstrates dynamic method dispatch
            System.out.println();
        }
    }
}
