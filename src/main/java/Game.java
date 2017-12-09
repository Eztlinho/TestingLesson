/**
 * Created by Steve on 7/5/2017.
 */
public class Game {

    public static Game instance;
    private Praetorian praetorian = new Praetorian();
    private Zombie zombie = new Zombie();
    private Wizard wizard = new Wizard();

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    public Praetorian getPraetorian() {
        return praetorian;
    }
    public Zombie getZombie() {
        return zombie;
    }
    public Wizard getWizard() {
        return wizard;
    }
}
