import java.util.Random;
import java.util.UUID;

class VirtualPet {
    final String petId;
    String petName;
    String species;
    int age;
    int happiness;
    int health;
    String currentStage;
    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated = 0;
    boolean isGhost = false;

    public VirtualPet() {
        this(generatePetId(), randomName(), randomSpecies(), 0, 5, 10, EVOLUTION_STAGES);
    }

    public VirtualPet(String petName) {
        this(generatePetId(), petName, randomSpecies(), 1, 10, 10, EVOLUTION_STAGES[1]);
    }

    public VirtualPet(String petName, String species) {
        this(generatePetId(), petName, species, 2, 15, 14, EVOLUTION_STAGES[2]);
    }

    public VirtualPet(String petId, String petName, String species, int age, int happiness, int health, String currentStage) {
        this.petId = petId;
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.currentStage = currentStage;
        totalPetsCreated++;
    }

    static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    static String randomSpecies() {
        String[] s = {"Dragon", "Phoenix", "Unicorn", "Rabbit", "Dino"};
        return s[new Random().nextInt(s.length)];
    }

    static String randomName() {
        String[] n = {"Fluffy", "Spark", "Milo", "Luna", "Rex"};
        return n[new Random().nextInt(n.length)];
    }

    public void feedPet() {
        if (!isGhost) happiness += 2;
    }

    public void playWithPet() {
        if (!isGhost) happiness += 3;
    }

    public void healPet() {
        if (!isGhost) health = Math.min(20, health + 5);
    }

    public void simulateDay() {
        if (!isGhost) {
            age++;
            happiness = Math.max(0, happiness + new Random().nextInt(3) - 1);
            health = Math.max(0, health + new Random().nextInt(3) - 2);
            evolvePet();
            if (health == 0) {
                isGhost = true;
                species = "Ghost";
                currentStage = "Ghost";
            }
        }
    }

    public void evolvePet() {
        if (!isGhost) {
            int idx = age < EVOLUTION_STAGES.length ? age : EVOLUTION_STAGES.length-1;
            currentStage = EVOLUTION_STAGES[idx];
        }
    }

    public String getPetStatus() {
        return isGhost ? "Ghost" : currentStage;
    }

    public void hauntOtherPet(VirtualPet target) {
        if (isGhost) target.happiness = Math.max(0, target.happiness - 2);
    }

    public void displayProfile() {
        System.out.printf("Pet: %s | Species: %s | Age: %d | Stage: %s | Happy: %d | Health: %d | Status: %s\n",
            petName, species, age, currentStage, happiness, health, isGhost ? "Ghost" : "Alive");
    }

    public static void main(String[] args) {
        VirtualPet p1 = new VirtualPet();
        VirtualPet p2 = new VirtualPet("Berry");
        VirtualPet p3 = new VirtualPet("Nova", "Dragon");
        VirtualPet p4 = new VirtualPet(generatePetId(), "Sparky", "Phoenix", 5, 13, 8, "Teen");
        for(int day=0; day<8; day++) {
            p1.simulateDay(); p2.simulateDay(); p3.simulateDay(); p4.simulateDay();
        }
        p1.feedPet(); p2.playWithPet(); p3.healPet();
        if (p4.isGhost) p4.hauntOtherPet(p1);
        p1.displayProfile();
        p2.displayProfile();
        p3.displayProfile();
        p4.displayProfile();
        System.out.println("Total Pets: " + totalPetsCreated);
    }
}
