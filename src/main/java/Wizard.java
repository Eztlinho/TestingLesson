
public class Wizard extends Human {

    private int elixir = 10;
    private int defaultHealPower = 10;
    private boolean hasFailed = false;

    public Wizard() {
        super(1,5,100);
    }

    public double getHealPower() {
        if(elixir >= 2) {
            elixir -= 2;
            return level * 0.5 * defaultHealPower;
        }
        return 0;
    }

    private void fails() {
        if(hasFailed) {
            throw new IllegalStateException("I QUIT !");
        }
        hasFailed = true;
    }

    private boolean succeeds() {
        return Math.random()<0.95;
    }

    public int getElixir() {
        return elixir;
    }

    public void heal(Human human) {
        if (succeeds()) {
            hasFailed = false;
            human.setLife(human.getLife()+getHealPower());
        }
        else {
            fails();
        }
    }
}
