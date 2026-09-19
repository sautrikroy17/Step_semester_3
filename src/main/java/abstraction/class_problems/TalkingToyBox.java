package abstraction.class_problems;

abstract class Toy {
    private static int counter = 1000;
    final String toyId;
    protected String name;

    public Toy(String name) {
        this.name = name;
        this.toyId = "TOY-" + (++counter);
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }

    public String getName() {
        return name;
    }
}

class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}

public class TalkingToyBox {
    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound());

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound());

        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}
