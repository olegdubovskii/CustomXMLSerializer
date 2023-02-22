package animals;

public abstract class Animal implements Cloneable {
    public String name;
    public Physique physicalData;
    public int age;
    public String ownerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Physique getPhysicalData() {
        return physicalData;
    }

    public void setPhysicalData(Physique physicalData) {
        this.physicalData = physicalData;
    }

    public String outFields() {
        return "Name: " + this.name +
                "\nAge: " + this.age +
                "\nOwnerName: " + this.ownerName +
                "\nWeight: " + this.physicalData.weight +
                "\nHeight: " + this.physicalData.height + "\n\n";
    }

    public abstract void eat();

    @Override
    public Animal clone() throws CloneNotSupportedException {
        Animal newAnimal = (Animal) super.clone();
        Physique newPhysique = this.getPhysicalData().clone();
        newAnimal.setPhysicalData(newPhysique);
        return newAnimal;
    }

    public Animal() {
        this.physicalData = new Physique();
    }

    public static Animal createAnimal(String nameOfClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(nameOfClass);
        Animal result = (Animal) clazz.newInstance();
        return result;
    }
}
