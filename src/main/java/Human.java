/**
 * Created by Steve on 7/4/2017.
 */
public abstract class Human {

    public static final String VICTIM_IS_ALREADY_DEAD = "Victim is already dead";
    protected double level;
    protected double force;
    protected double life;

    public Human (double level, double force, double life) {
        this.level = level;
        this.force = force;
        this.life = life;
    }

    public double getLife() {
        return life;
    }

    public double getHitForce() {
        return force + (force * level / 10);
    }

    public void hit(Human victim) {
        if(victim.getLife()<=0) {
            throw new IllegalStateException(VICTIM_IS_ALREADY_DEAD);
        }
        victim.setLife(victim.getLife() - getHitForce());
    }

    public void setLife(double life) {
        this.life = life;
    }
}
