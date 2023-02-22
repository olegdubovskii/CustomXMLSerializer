package animals;

public class Physique implements Cloneable {

    public int height;
    public TestClass testField1;
    public int weight;
    public TestClass testField2;

    public Physique() {
        this.testField1 = new TestClass(1.0, 1.0);
        this.testField2 = new TestClass(9.0, 9.0);
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public Physique clone() throws CloneNotSupportedException {
        return (Physique) super.clone();
    }
}
