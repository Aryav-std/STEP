import java.util.Arrays;
import java.util.UUID;

// ===================== Immutable PetSpecies =====================
final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isEmpty() || evolutionStages == null || evolutionStages.length == 0
                || maxLifespan <= 0 || habitat == null || habitat.isEmpty()) {
            throw new IllegalArgumentException("Invalid species data");
        }
        this.speciesName = speciesName;
        this.evolutionStages = evolutionStages.clone(); // defensive copy
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return evolutionStages.clone(); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() {
        return speciesName + " (Habitat: " + habitat + ", Lifespan: " + maxLifespan + ")";
    }
}

// ===================== VirtualPet Class =====================
class VirtualPet {
    // Immutable core
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    // Controlled mutable state
    private String petName;
    private int age;
    private int happiness;
    private int health;

    // Package/protected constants
    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Baby", "Teen", "Adult"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;

    // Global access
    public static final String PET_SYSTEM_VERSION = "2.0";

    // ================== Constructors ==================
    public VirtualPet() {
        this("RandomPet", new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 20, "Forest"), 1, 50, 50);
    }

    public VirtualPet(String name) {
        this(name, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 20, "Forest"), 1, 50, 50);
    }

    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 1, 50, 50);
    }

    public VirtualPet(String name, PetSpecies species, int age, int happiness, int health) {
        this.petId = UUID.randomUUID().toString();
        this.species = species;
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = name;
        this.age = validateStat(age);
        this.happiness = validateStat(happiness);
        this.health = validateStat(health);
    }

    // ================== Helper Methods ==================
    private int validateStat(int value) {
        if (value < 0) return 0;
        if (value > 100) return 100;
        return value;
    }

    private void modifyHappiness(int delta) {
        happiness = validateStat(happiness + delta);
    }

    private void modifyHealth(int delta) {
        health = validateStat(health + delta);
    }

    private void updateEvolutionStage() {
        System.out.println(petName + " is evolving...");
    }

    protected int calculateFoodBonus(String foodType) {
        return foodType.length() % 10 + 5;
    }

    protected int calculateGameEffect(String gameType) {
        return gameType.length() % 15 + 10;
    }

    // ================== Public Methods ==================
    public void feedPet(String foodType) {
        int bonus = calculateFoodBonus(foodType);
        modifyHappiness(bonus);
        modifyHealth(bonus / 2);
        System.out.println(petName + " fed with " + foodType + ". Happiness +" + bonus);
    }

    public void playWithPet(String gameType) {
        int effect = calculateGameEffect(gameType);
        modifyHappiness(effect);
        System.out.println(petName + " played " + gameType + ". Happiness +" + effect);
    }

    // Package-private method
    String getInternalState() {
        return "PetID: " + petId + ", Name: " + petName + ", Age: " + age +
               ", Happiness: " + happiness + ", Health: " + health +
               ", Species: " + species.getSpeciesName();
    }

    // ================== Getters/Setters ==================
    public String getPetId() { return petId; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = validateStat(age); }

    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness); }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health); }

    public PetSpecies getSpecies() { return species; }

    @Override
    public String toString() {
        return "VirtualPet{" + petName + ", Species=" + species.getSpeciesName() +
               ", Age=" + age + ", Happiness=" + happiness + ", Health=" + health + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VirtualPet)) return false;
        VirtualPet other = (VirtualPet) obj;
        return petId.equals(other.petId);
    }

    @Override
    public int hashCode() {
        return petId.hashCode();
    }
}

// ===================== Specialized Pet Classes =====================
class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet pet;

    public DragonPet(VirtualPet pet, String dragonType, String breathWeapon) {
        this.pet = pet;
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
    }

    public void roar() { System.out.println(pet.getPetName() + " the " + dragonType + " roars!"); }
}

class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet pet;

    public RobotPet(VirtualPet pet, boolean needsCharging, int batteryLevel) {
        this.pet = pet;
        this.needsCharging = needsCharging;
        this.batteryLevel = batteryLevel;
    }

    public void charge() {
        batteryLevel = 100;
        needsCharging = false;
        System.out.println(pet.getPetName() + " charged to full battery!");
    }
}

// ===================== Main Testing =====================
public class VirtualPetSystem {
    public static void main(String[] args) {
        PetSpecies dragonSpecies = new PetSpecies("Dragon", new String[]{"Hatchling","Young","Adult"}, 50, "Volcano");
        VirtualPet pet1 = new VirtualPet("Firedrake", dragonSpecies);
        VirtualPet pet2 = new VirtualPet("RoboBuddy");

        System.out.println("--- Virtual Pets ---");
        System.out.println(pet1);
        System.out.println(pet2);

        pet1.feedPet("Meat");
        pet2.playWithPet("Fetch Game");

        System.out.println("\n--- Internal State ---");
        System.out.println(pet1.getInternalState());

        DragonPet dragon = new DragonPet(pet1, "Fire Dragon", "Flame Breath");
        RobotPet robot = new RobotPet(pet2, true, 80);

        System.out.println("\n--- Specialized Pets ---");
        dragon.roar();
        robot.charge();
    }
}
