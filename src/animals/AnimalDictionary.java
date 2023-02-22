package animals;

import java.util.HashMap;
import java.util.Map;

public class AnimalDictionary {

    public Map<String, Animal> dictionary;

    public AnimalDictionary() {
        dictionary = new HashMap<>();
        dictionary.put("Dog", new Dog());
        dictionary.put("Cat", new Cat());
        dictionary.put("Horse", new Horse());
        dictionary.put("Monkey", new Monkey());
        dictionary.put("Elephant", new Elephant());
    }
}
