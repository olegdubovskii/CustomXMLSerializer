package animals;

public class Elephant extends Animal{
    @Override
    public void eat() {
        System.out.println("An elephant " + this.name + " eats apples");
    }
}
