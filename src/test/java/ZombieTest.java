import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Steve on 7/5/2017.
 */
public class ZombieTest {
    public static final ReflectionTestUtils REFLECTION_TEST_UTILS = ReflectionTestUtils.getInstance();

    private Zombie zombie = new Zombie();
    @Test
    public void getHitForce_15ForceAndLevel1_165() {
        Assert.assertEquals(zombie.getHitForce(), 2.2, 0.1);
    }

    @Test
    public void defaultConstructor() {
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(zombie,"force"), 2, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(zombie,"level"), 1, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(zombie,"life"), 30, 0.1);
    }

}
