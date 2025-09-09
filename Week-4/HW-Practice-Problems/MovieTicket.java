public class MovieTicket {
    String movieName;
    String theatreName;
    int seatNumber;
    double price;

    // Default constructor assigns "Unknown" movie
    public MovieTicket() {
        movieName = "Unknown";
        theatreName = "Unknown";
        seatNumber = 0;
        price = 0;
    }

    // Constructor with movie name, default price 200
    public MovieTicket(String movieName) {
        this.movieName = movieName;
        theatreName = "Unknown";
        seatNumber = 0;
        price = 200;
    }

    // Constructor with movie name and seat number, default theatre "PVR"
    public MovieTicket(String movieName, int seatNumber) {
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        theatreName = "PVR";
        price = 200;
    }

    // Full constructor sets all details
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Method to display ticket details
    public void printTicket() {
        System.out.println("Movie: " + movieName + ", Theatre: " + theatreName + ", Seat: " + seatNumber + ", Price: " + price);
    }

    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Interstellar");
        MovieTicket t3 = new MovieTicket("Inception", 45);
        MovieTicket t4 = new MovieTicket("Dune", "Regal Cinema", 27, 350);
        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
