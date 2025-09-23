import java.util.*;

// ===================== Immutable KingdomConfig =====================
final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes,
                         Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.isEmpty() || foundingYear <= 0 || allowedStructureTypes == null
                || allowedStructureTypes.length == 0 || resourceLimits == null || resourceLimits.isEmpty()) {
            throw new IllegalArgumentException("Invalid kingdom configuration");
        }
        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = allowedStructureTypes.clone();
        this.resourceLimits = new HashMap<>(resourceLimits);
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return allowedStructureTypes.clone(); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    // Factory methods
    public static KingdomConfig createDefaultKingdom() {
        return new KingdomConfig("Default Kingdom", 1000, new String[]{"WizardTower", "Castle", "Library", "DragonLair"},
                Map.of("Gold", 1000, "Food", 500, "Mana", 300));
    }

    public static KingdomConfig createFromTemplate(String type) {
        switch (type.toLowerCase()) {
            case "fortress":
                return new KingdomConfig("Fortress Kingdom", 1200, new String[]{"Castle", "Library"},
                        Map.of("Gold", 2000, "Food", 800, "Mana", 100));
            default:
                return createDefaultKingdom();
        }
    }

    @Override
    public String toString() {
        return kingdomName + " (Founded: " + foundingYear + ")";
    }
}

// ===================== Base MagicalStructure =====================
class MagicalStructure {
    private final String structureId;
    private final long constructionTimestamp;
    private final String structureName;
    private final String location;

    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    // Constructor chaining
    public MagicalStructure(String name, String location) {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || name.isEmpty() || location == null || location.isEmpty() ||
            power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) {
            throw new IllegalArgumentException("Invalid structure data");
        }
        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = power;
        this.isActive = active;
        this.currentMaintainer = "Unknown";
    }

    public void setCurrentMaintainer(String name) { this.currentMaintainer = name; }
    public String getCurrentMaintainer() { return currentMaintainer; }

    public int getMagicPower() { return magicPower; }
    public void setMagicPower(int power) { this.magicPower = Math.max(MIN_MAGIC_POWER, Math.min(MAX_MAGIC_POWER, power)); }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }

    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }

    @Override
    public String toString() {
        return structureName + " at " + location + " [Magic: " + magicPower + "]";
    }
}

// ===================== Specialized Structures =====================
class WizardTower {
    private final int maxSpellCapacity;
    private final List<String> knownSpells;
    private String currentWizard;

    public WizardTower(int maxSpellCapacity, List<String> spells, String wizard) {
        this.maxSpellCapacity = maxSpellCapacity;
        this.knownSpells = new ArrayList<>(spells);
        this.currentWizard = wizard;
    }

    public int getMaxSpellCapacity() { return maxSpellCapacity; }
    public List<String> getKnownSpells() { return new ArrayList<>(knownSpells); }
    public String getCurrentWizard() { return currentWizard; }
    public void setCurrentWizard(String wizard) { this.currentWizard = wizard; }

    @Override
    public String toString() {
        return "WizardTower (Wizard: " + currentWizard + ", Spells: " + knownSpells + ")";
    }
}

class EnchantedCastle {
    private final String castleType;
    private final int defenseRating;
    private final boolean hasDrawbridge;

    public EnchantedCastle(String castleType, int defenseRating, boolean hasDrawbridge) {
        this.castleType = castleType;
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    @Override
    public String toString() {
        return castleType + " Castle [Defense: " + defenseRating + ", Drawbridge: " + hasDrawbridge + "]";
    }
}

class MysticLibrary {
    private final Map<String, String> bookCollection;
    private final int knowledgeLevel;

    public MysticLibrary(Map<String, String> collection, int level) {
        this.bookCollection = new HashMap<>(collection);
        this.knowledgeLevel = level;
    }

    public Map<String, String> getBookCollection() { return new HashMap<>(bookCollection); }
    public int getKnowledgeLevel() { return knowledgeLevel; }

    @Override
    public String toString() {
        return "MysticLibrary (Knowledge Level: " + knowledgeLevel + ", Books: " + bookCollection.keySet() + ")";
    }
}

class DragonLair {
    private final String dragonType;
    private final long treasureValue;
    private final int territorialRadius;

    public DragonLair(String type, long treasure, int radius) {
        this.dragonType = type;
        this.treasureValue = treasure;
        this.territorialRadius = radius;
    }

    @Override
    public String toString() {
        return dragonType + " Lair [Treasure: " + treasureValue + ", Radius: " + territorialRadius + "]";
    }
}

// ===================== KingdomManager =====================
class KingdomManager {
    private final List<Object> structures = new ArrayList<>();
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        this.config = config;
    }

    public void addStructure(Object structure) { structures.add(structure); }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        if (s1 instanceof WizardTower && s2 instanceof EnchantedCastle) return true;
        if (s1 instanceof DragonLair && s2 instanceof WizardTower) return false;
        return true; // default
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker instanceof WizardTower && defender instanceof DragonLair) return "WizardTower wins!";
        return "Draw";
    }

    public static int calculateKingdomPower(Object[] structures) {
        int total = 0;
        for (Object s : structures) {
            if (s instanceof WizardTower wt) total += wt.getMaxSpellCapacity();
            if (s instanceof EnchantedCastle ec) total += ec.defenseRating;
            if (s instanceof MysticLibrary ml) total += ml.getKnowledgeLevel();
        }
        return total;
    }

    private String determineStructureCategory(Object structure) {
        if (structure instanceof WizardTower) return "WizardTower";
        if (structure instanceof EnchantedCastle) return "EnchantedCastle";
        if (structure instanceof MysticLibrary) return "MysticLibrary";
        if (structure instanceof DragonLair) return "DragonLair";
        return "Unknown";
    }

    @Override
    public String toString() {
        return "KingdomManager managing " + structures.size() + " structures in " + config.getKingdomName();
    }
}

// ===================== Main Test =====================
public class MedievalKingdomSystem {
    public static void main(String[] args) {
        KingdomConfig config = KingdomConfig.createDefaultKingdom();
        KingdomManager manager = new KingdomManager(config);

        WizardTower tower = new WizardTower(50, List.of("Fireball", "Teleport"), "Gandalf");
        EnchantedCastle castle = new EnchantedCastle("Royal", 200, true);
        MysticLibrary library = new MysticLibrary(Map.of("Book of Spells", "Magic"), 80);
        DragonLair lair = new DragonLair("Red Dragon", 100000, 50);

        manager.addStructure(tower);
        manager.addStructure(castle);
        manager.addStructure(library);
        manager.addStructure(lair);

        System.out.println("--- Kingdom Structures ---");
        System.out.println(tower);
        System.out.println(castle);
        System.out.println(library);
        System.out.println(lair);

        System.out.println("\n--- Interaction Tests ---");
        System.out.println("Tower vs Castle: " + KingdomManager.canStructuresInteract(tower, castle));
        System.out.println("Tower vs Lair Battle: " + KingdomManager.performMagicBattle(tower, lair));

        System.out.println("\n--- Kingdom Power ---");
        System.out.println("Total Power: " + KingdomManager.calculateKingdomPower(new Object[]{tower, castle, library}));
    }
}
