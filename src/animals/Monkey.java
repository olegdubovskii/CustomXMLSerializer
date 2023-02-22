package animals;

public class Monkey extends Animal {
    @Override
    public void eat() {
        System.out.println("A monkey " + this.name + " eats bananas");
    }
}
