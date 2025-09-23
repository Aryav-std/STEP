// Parent class
class Box {
    protected String content;

    public Box() {
        this.content = "Empty";
        System.out.println("Box default constructor called");
    }

    public Box(String content) {
        this.content = content;
        System.out.println("Box parameterized constructor called with content: " + content);
    }

    public void pack(String item) {
        content = item;
        System.out.println("Box packed with: " + content);
    }

    public void unpack() {
        System.out.println("Box unpacked. Content was: " + content);
        content = "Empty";
    }
}

// Child class - GiftBox
class GiftBox extends Box {
    private String ribbonColor;

    public GiftBox() {
        super();
        this.ribbonColor = "Red";
        System.out.println("GiftBox default constructor called");
    }

    public GiftBox(String content, String ribbonColor) {
        super(content);
        this.ribbonColor = ribbonColor;
        System.out.println("GiftBox parameterized constructor called");
    }

    @Override
    public void pack(String item) {
        super.pack(item); // call parent pack
        System.out.println("Adding decorative ribbon: " + ribbonColor);
    }

    @Override
    public void unpack() {
        System.out.println("GiftBox ribbon color: " + ribbonColor);
        super.unpack(); // call parent unpack
        System.out.println("GiftBox is now empty and ready for reuse!");
    }
}

// Test class
public class BoxTest {
    public static void main(String[] args) {
        System.out.println("--- Testing Box ---");
        Box box = new Box();
        box.pack("Books");
        box.unpack();

        System.out.println("\n--- Testing GiftBox ---");
        GiftBox giftBox = new GiftBox("Teddy Bear", "Gold");
        giftBox.pack("Chocolate");
        giftBox.unpack();
    }
}
