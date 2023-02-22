package animals;

public class Dog extends Animal {

    @Override
    public void eat() {
        System.out.println("A dog " + this.name + " eats pork");
    }
}
