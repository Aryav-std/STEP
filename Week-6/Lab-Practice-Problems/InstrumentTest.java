// Base class
class Instrument {
    protected String name;
    protected String material;

    public Instrument() {
        this.name = "Unknown Instrument";
        this.material = "Unknown Material";
        System.out.println("Instrument default constructor called");
    }

    public Instrument(String name, String material) {
        this.name = name;
        this.material = material;
        System.out.println("Instrument parameterized constructor called: " + name);
    }

    public void play() {
        System.out.println(name + " is playing a generic tune.");
    }

    public void showInfo() {
        System.out.println("Instrument: " + name + ", Material: " + material);
    }
}

// Child class - Piano
class Piano extends Instrument {
    private int keys;

    public Piano() {
        super();
        this.keys = 88;
        System.out.println("Piano default constructor called");
    }

    public Piano(String name, String material, int keys) {
        super(name, material);
        this.keys = keys;
        System.out.println("Piano parameterized constructor called");
    }

    @Override
    public void play() {
        System.out.println(name + " is playing a piano melody.");
    }

    public void showPianoInfo() {
        showInfo();
        System.out.println("Keys: " + keys);
    }
}

// Child class - Guitar
class Guitar extends Instrument {
    private int strings;

    public Guitar() {
        super();
        this.strings = 6;
        System.out.println("Guitar default constructor called");
    }

    public Guitar(String name, String material, int strings) {
        super(name, material);
        this.strings = strings;
        System.out.println("Guitar parameterized constructor called");
    }

    @Override
    public void play() {
        System.out.println(name + " is strumming guitar chords.");
    }

    public void showGuitarInfo() {
        showInfo();
        System.out.println("Strings: " + strings);
    }
}

// Child class - Drum
class Drum extends Instrument {
    private String drumType;

    public Drum() {
        super();
        this.drumType = "Snare";
        System.out.println("Drum default constructor called");
    }

    public Drum(String name, String material, String drumType) {
        super(name, material);
        this.drumType = drumType;
        System.out.println("Drum parameterized constructor called");
    }

    @Override
    public void play() {
        System.out.println(name + " is beating the drum rhythm.");
    }

    public void showDrumInfo() {
        showInfo();
        System.out.println("Drum Type: " + drumType);
    }
}

// Test class
public class InstrumentTest {
    public static void main(String[] args) {
        // Using hierarchical inheritance
        Piano piano = new Piano("Yamaha", "Wood", 88);
        Guitar guitar = new Guitar("Fender", "Wood", 6);
        Drum drum = new Drum("Pearl", "Metal", "Bass");

        System.out.println("\n--- Playing Instruments ---");
        Instrument[] instruments = {piano, guitar, drum};
        for (Instrument inst : instruments) {
            inst.play(); // polymorphic call
        }

        System.out.println("\n--- Showing Detailed Info ---");
        piano.showPianoInfo();
        guitar.showGuitarInfo();
        drum.showDrumInfo();
    }
}
