package animals;

public class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("A cat " + this.name + " eats chicken");
    }
}
