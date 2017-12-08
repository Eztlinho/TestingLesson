import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Steve on 7/5/2017.
 */
public class ZombieTest {

    private Zombie zombie = new Zombie();
    @Test
    public void getHitForce_15ForceAndLevel1_165() {
        Assert.assertEquals(zombie.getHitForce(), 2.2, 0.1);
    }

}
