public class Praetorian extends  Human{

    public Praetorian() {
        super(1,15, 150);
    };

    public double getHitForce() {
        return isLuckyHit() ? getCriticalHitForce() : getDefaultHitForce();
    }

    private boolean isLuckyHit() {
        return Math.random()>0.95;
    }

    private double getCriticalHitForce() {
        return force * 3 + level;
    }

    private double getDefaultHitForce() {
        return force + (force * level / 10);
    }
}

