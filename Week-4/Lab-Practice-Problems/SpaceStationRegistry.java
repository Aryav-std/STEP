import java.util.Random;
import java.util.UUID;

enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);
    private final int level;
    CrewRank(int level) { this.level = level; }
    public int getLevel() { return level; }
}

class SpaceCrew {
    final String crewId;
    final String homeplanet;
    final CrewRank initialRank;
    String currentRank;
    int skillLevel;
    int missionCount;
    int spaceHours;
    static final String STATION_NAME = "Stellar Odyssey";
    static final int MAX_CREW_CAPACITY = 50;

    public SpaceCrew(String name) {
        this(UUID.randomUUID().toString(), randomPlanet(), CrewRank.CADET, "CADET", 10, 0, 0);
    }

    public SpaceCrew(String name, String homeplanet, CrewRank rank) {
        this(UUID.randomUUID().toString(), homeplanet, rank, rank.name(), 10, 0, 0);
    }

    public SpaceCrew(String name, String homeplanet, CrewRank rank, int missions, int skillLevel, int spaceHours) {
        this(UUID.randomUUID().toString(), homeplanet, rank, rank.name(), skillLevel, missions, spaceHours);
    }

    public SpaceCrew(String crewId, String homeplanet, CrewRank initialRank, String currentRank, int skillLevel, int missionCount, int spaceHours) {
        this.crewId = crewId;
        this.homeplanet = homeplanet;
        this.initialRank = initialRank;
        this.currentRank = currentRank;
        this.skillLevel = skillLevel;
        this.missionCount = missionCount;
        this.spaceHours = spaceHours;
    }

    static String randomPlanet() {
        String[] planets = {"Earth", "Mars", "Venus", "Jupiter", "Neptune"};
        return planets[new Random().nextInt(planets.length)];
    }

    final String getCrewIdentification() {
        return crewId + "@" + homeplanet;
    }

    final boolean canBePromoted() {
        return CrewRank.valueOf(currentRank).getLevel() < CrewRank.ADMIRAL.getLevel();
    }

    final int calculateSpaceExperience() {
        return missionCount * 10 + spaceHours + skillLevel * 2;
    }

    public void displayProfile() {
        System.out.printf("CrewID: %s | Origin: %s | InitRank: %s | Current: %s | Missions: %d | Hours: %d | Skill: %d\n",
            crewId, homeplanet, initialRank.name(), currentRank, missionCount, spaceHours, skillLevel);
    }
}

final class SpaceStationRegistry {
    static SpaceCrew[] crewList = new SpaceCrew[SpaceCrew.MAX_CREW_CAPACITY];
    static int crewCount = 0;

    static void addCrew(SpaceCrew sc) {
        if (crewCount < crewList.length) crewList[crewCount++] = sc;
    }

    static void displayAllCrew() {
        for (int i=0; i<crewCount; i++) crewList[i].displayProfile();
        System.out.println("Total Crew: " + crewCount);
    }

    static int crewForEmergency() {
        int eligible = 0;
        for (int i=0; i<crewCount; i++)
            if (crewList[i].canBePromoted()) eligible++;
        return eligible;
    }

    public static void main(String[] args) {
        SpaceCrew c1 = new SpaceCrew("Alice");
        SpaceCrew c2 = new SpaceCrew("Bob", "Mars", CrewRank.OFFICER);
        SpaceCrew c3 = new SpaceCrew("Eve", "Venus", CrewRank.COMMANDER, 3, 15, 500);
        SpaceCrew c4 = new SpaceCrew(UUID.randomUUID().toString(), "Jupiter", CrewRank.CAPTAIN, "CAPTAIN", 18, 7, 250);
        addCrew(c1); addCrew(c2); addCrew(c3); addCrew(c4);
        displayAllCrew();
        System.out.println("Emergency Eligible Crew: " + crewForEmergency());
    }
}
