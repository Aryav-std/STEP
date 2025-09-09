import java.util.*;

enum PersonalityType { HEROIC, VILLAINOUS, MYSTERIOUS, COMICAL }

class StoryCharacter {
    final String characterId;
    final String backstory;
    final PersonalityType corePersonality;
    String currentMood;
    Map<String, Integer> relationshipMap;
    Set<String> skillSet;
    String currentLocation;
    static final String[] STORY_GENRES = {"Fantasy", "Sci-Fi", "Mystery", "Romance", "Adventure"};

    public StoryCharacter(String characterId, String backstory, PersonalityType corePersonality) {
        this.characterId = characterId;
        this.backstory = backstory;
        this.corePersonality = corePersonality;
        this.currentMood = "Neutral";
        this.relationshipMap = new HashMap<>();
        this.skillSet = new HashSet<>();
        this.currentLocation = "Prologue";
    }
}

class Hero extends StoryCharacter {
    final String originStory;
    final Set<String> abilities;

    public Hero(String charId, String backstory, String originStory) {
        super(charId, backstory, PersonalityType.HEROIC);
        this.originStory = originStory;
        this.abilities = new HashSet<>();
        this.abilities.add("Bravery");
        this.skillSet.add("Swordplay");
    }
}

class Villain extends StoryCharacter {
    final String evilMotive;
    public Villain(String charId, String backstory, String evilMotive) {
        super(charId, backstory, PersonalityType.VILLAINOUS);
        this.evilMotive = evilMotive;
        this.skillSet.add("Scheming");
    }
}

class MysteriousStranger extends StoryCharacter {
    public MysteriousStranger(String charId, String backstory) {
        super(charId, backstory, PersonalityType.MYSTERIOUS);
    }
}

class ComicRelief extends StoryCharacter {
    final String humorStyle;
    public ComicRelief(String charId, String backstory, String humorStyle) {
        super(charId, backstory, PersonalityType.COMICAL);
        this.humorStyle = humorStyle;
        this.skillSet.add("Jokes");
    }
}

class StoryEngine {
    static void generateStoryArc(StoryCharacter... chars) {
        for (StoryCharacter ch : chars) {
            if (ch instanceof Hero) System.out.println("Epic Quest begins for " + ch.characterId);
            else if (ch instanceof Villain) System.out.println("Villain plots " + ((Villain)ch).evilMotive);
            else if (ch instanceof MysteriousStranger) System.out.println("Stranger alters the story!");
            else if (ch instanceof ComicRelief) System.out.println("Jokes lighten up " + ch.characterId);
        }
    }

    static void resolveConflict(StoryCharacter c1, StoryCharacter c2) {
        if (c1 instanceof Hero && c2 instanceof Villain)
            System.out.println("Hero and Villain clash for destiny!");
        else if (c1 instanceof MysteriousStranger)
            System.out.println("Mysterious events unfold...");
    }

    static void createDialogue(StoryCharacter c) {
        if (c instanceof Hero) System.out.println("I'm ready for anything!");
        else if (c instanceof Villain) System.out.println("My evil will prevail...");
        else if (c instanceof ComicRelief) System.out.println("Knock Knock!");
        else System.out.println("...");
    }

    public static void main(String[] args) {
        Hero h = new Hero("H001", "Lost Prince", "RoyalBlood");
        Villain v = new Villain("V002", "Abandoned Child", "World Domination");
        MysteriousStranger m = new MysteriousStranger("M003", "Unknown");
        ComicRelief c = new ComicRelief("C004", "Court Jester", "Slapstick");
        generateStoryArc(h, v, m, c);
        resolveConflict(h, v);
        resolveConflict(h, m);
        createDialogue(h); createDialogue(v); createDialogue(c);
        System.out.println("Story Genres: " + Arrays.toString(StoryCharacter.STORY_GENRES));
    }
}
