package animals;

public class Horse extends Animal{
    @Override
    public void eat() {
        System.out.println("A horse " + this.name + " eats grass");
    }
}
