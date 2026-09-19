interface Washable {
    String clean();
}

abstract class KitchenTool {
    private int speedLevel;

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        } else {
            System.out.println("rejected, speed level stays " + this.speedLevel);
        }
    }

    public abstract String prepare();
}

class Blender extends KitchenTool implements Washable {
    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class SmartKitchenAssistant {
    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel());

        b.setSpeedLevel(9);

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}
