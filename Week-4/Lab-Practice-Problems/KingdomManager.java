abstract class MagicalStructure {
    String structureName;
    int magicPower;
    String location;
    boolean isActive;

    public MagicalStructure(String structureName) {
        this(structureName, 10, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location) {
        this(structureName, magicPower, location, true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    abstract void castMagicSpell();
}

class WizardTower extends MagicalStructure {
    int spellCapacity;
    String[] knownSpells;

    public WizardTower(String name) {
        this(name, 30, "Wizard's Hill", true, 3, new String[]{"Fireball", "Shield", "Teleport"});
    }

    public WizardTower(String name, int spellCapacity, String[] spells) {
        this(name, 50, "Wizard's Hill", true, spellCapacity, spells);
    }

    public WizardTower(String name, int magicPower, String loc, boolean active, int spellCapacity, String[] spells) {
        super(name, magicPower, loc, active);
        this.spellCapacity = spellCapacity;
        this.knownSpells = spells;
    }

    void castMagicSpell() {
        System.out.println(structureName + " casts: " + knownSpells);
    }
}

class EnchantedCastle extends MagicalStructure {
    int defenseRating;
    boolean hasDrawbridge;

    public EnchantedCastle(String name) {
        this(name, 70, "Castle Hill", true, 100, true);
    }

    public EnchantedCastle(String name, int defenseRating) {
        this(name, 70, "Castle Hill", true, defenseRating, true);
    }

    public EnchantedCastle(String name, int magicPower, String loc, boolean active, int defenseRating, boolean hasDrawbridge) {
        super(name, magicPower, loc, active);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    void castMagicSpell() {
        System.out.println(structureName + " activates mystical shields!");
    }
}

class MysticLibrary extends MagicalStructure {
    int bookCount;
    String ancientLanguage;

    public MysticLibrary(String name) {
        this(name, 20, "Library Lane", true, 1000, "Latin");
    }

    public MysticLibrary(String name, int bookCount, String language) {
        this(name, 20, "Library Lane", true, bookCount, language);
    }

    public MysticLibrary(String name, int magicPower, String loc, boolean active, int bookCount, String ancientLanguage) {
        super(name, magicPower, loc, active);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    void castMagicSpell() {
        System.out.println(structureName + " uncovers ancient knowledge!");
    }
}

class DragonLair extends MagicalStructure {
    String dragonType;
    int treasureValue;

    public DragonLair(String name) {
        this(name, 90, "Cave", true, "Fire Drake", 50000);
    }

    public DragonLair(String name, String dragonType) {
        this(name, 90, "Cave", true, dragonType, 50000);
    }

    public DragonLair(String name, int magicPower, String loc, boolean active, String dragonType, int treasureValue) {
        super(name, magicPower, loc, active);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    void castMagicSpell() {
        System.out.println(structureName + "'s dragon breathes fire!");
    }
}

class KingdomManager {
    static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        if ((s1 instanceof WizardTower && s2 instanceof MysticLibrary) || (s1 instanceof MysticLibrary && s2 instanceof WizardTower)) return true;
        if ((s1 instanceof EnchantedCastle && s2 instanceof DragonLair) || (s1 instanceof DragonLair && s2 instanceof EnchantedCastle)) return true;
        return false;
    }

    static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        int ap = attacker.magicPower;
        int dp = defender.magicPower;
        if (attacker instanceof WizardTower && defender instanceof EnchantedCastle) ap *= 2;
        if (attacker instanceof DragonLair && defender instanceof EnchantedCastle) dp *= 3;
        return ap > dp ? attacker.structureName + " wins" : defender.structureName + " wins";
    }

    static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int sum = 0;
        for (MagicalStructure s : structures) sum += s.magicPower;
        return sum;
    }

    public static void main(String[] args) {
        WizardTower tw1 = new WizardTower("North Tower");
        EnchantedCastle c1 = new EnchantedCastle("Crystal Fortress");
        MysticLibrary l1 = new MysticLibrary("Grand Library");
        DragonLair d1 = new DragonLair("Cave of Flames", "Black Wyrm");
        MagicalStructure[] structures = {tw1, c1, l1, d1};
        for (MagicalStructure s : structures) s.castMagicSpell();
        System.out.println("Can interact (Tower + Library): " + canStructuresInteract(tw1, l1));
        System.out.println("Magic Battle (Tower vs Castle): " + performMagicBattle(tw1, c1));
        System.out.println("Kingdom Magic Power: " + calculateKingdomMagicPower(structures));
    }
}
