// Parent class
class Game {
    protected String name;
    protected int maxPlayers;

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Game{name='" + name + "', maxPlayers=" + maxPlayers + "}";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Game other = (Game) obj;
        return maxPlayers == other.maxPlayers && name.equals(other.name);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return name.hashCode() + 31 * maxPlayers;
    }
}

// Child class
public class CardGame extends Game {
    private int numberOfCards;
    private String deckType;

    public CardGame(String name, int maxPlayers, int numberOfCards, String deckType) {
        super(name, maxPlayers);
        this.numberOfCards = numberOfCards;
        this.deckType = deckType;
    }

    // Override toString() and call super.toString()
    @Override
    public String toString() {
        return super.toString() + ", CardGame{numberOfCards=" + numberOfCards + ", deckType='" + deckType + "'}";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // check parent equality
        if (obj == null || getClass() != obj.getClass()) return false;
        CardGame other = (CardGame) obj;
        return numberOfCards == other.numberOfCards && deckType.equals(other.deckType);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return super.hashCode() + 31 * (numberOfCards + deckType.hashCode());
    }

    public static void main(String[] args) {
        Game game1 = new Game("Chess", 2);
        Game game2 = new Game("Chess", 2);
        CardGame card1 = new CardGame("Poker", 4, 52, "Standard");
        CardGame card2 = new CardGame("Poker", 4, 52, "Standard");

        System.out.println("--- Game Objects ---");
        System.out.println(game1);
        System.out.println("game1 equals game2? " + game1.equals(game2));

        System.out.println("\n--- CardGame Objects ---");
        System.out.println(card1);
        System.out.println("card1 equals card2? " + card1.equals(card2));

        System.out.println("\n--- Cross Comparison ---");
        System.out.println("game1 equals card1? " + game1.equals(card1));
    }
}
