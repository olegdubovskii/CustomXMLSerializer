package animals;

import java.util.ArrayList;

public class AnimalList {

    private ArrayList<Animal> animalList = new ArrayList<>();

    public void add(Animal elem) {
        animalList.add(elem);
    }

    public void remove(Animal elem) {
        animalList.remove(elem);
    }

    public void replace(Animal elem) {
        Animal temporaryAnimal;
        for (int i = 0; i<animalList.size(); i++) {
            temporaryAnimal = animalList.get(i);
            if (temporaryAnimal.getName().equals(elem.getName())
                    && temporaryAnimal.getAge() == elem.getAge()
                    && temporaryAnimal.getOwnerName().equals(elem.getOwnerName())
                    && temporaryAnimal.getPhysicalData().equals(elem.getPhysicalData())) {
                animalList.set(i, elem);
                break;
            }
        }
    }

    public Animal getElem(int index) {
        return animalList.get(index);
    }

    public ArrayList<Animal> getList() {
        return animalList;
    }

    public int getSize() {
        return animalList.size();
    }

}
