import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Praetorian.class})
public class PraetorianTest {
    public static final String VICTIM_IS_ALREADY_DEAD = "Victim is already dead";
    public static final ReflectionTestUtils REFLECTION_TEST_UTILS = ReflectionTestUtils.getInstance();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Praetorian praetorian = new Praetorian();

    @Mock
    private Human victim;

    @Before
    public void init() {
        PowerMockito.mockStatic(Math.class);
        Mockito.when(Math.random()).thenReturn(0.5d);
    }

    @Test
    public void defaultConstructor() {
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(praetorian,"force"), 15.0, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(praetorian,"level"), 1, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(praetorian,"life"), 150, 0.1);
    }

    @Test
    public void getHitForce_15ForceAndLevel1_165() {
        Assert.assertEquals(praetorian.getHitForce(), 16.5, 0.1);
    }

    @Test
    public void getLife_isEqualsTo_150() {
        Assert.assertEquals(praetorian.getLife(), 150, 1);
    }

    @Test
    public void getHitForce_20ForceAndLevel3() {
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "force",20);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "level",3);
        Assert.assertEquals(praetorian.getHitForce(), 26.0, 0.1);
    }

    @Test
    public void getHitForce_RandomHigherThan95_CriticalForce() {
        Mockito.when(Math.random()).thenReturn(0.96d);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "force",20);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "level",3);
        Assert.assertEquals(praetorian.getHitForce(), 63.0, 0.1);
    }

    @Test
    public void hit_setVictimsLife() {
        Mockito.when(victim.getLife()).thenReturn(180d);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "force",20);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "level",3);

        praetorian.hit(victim);

        Mockito.verify(victim).setLife(154d);
    }

    @Test
    public void hit_victimAlreadyDead_ThrowException() {
        expectedException.expectMessage(VICTIM_IS_ALREADY_DEAD);
        expectedException.expect(IllegalStateException.class);

        Mockito.when(victim.getLife()).thenReturn(0d);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "force",20);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(praetorian, "level",3);

        praetorian.hit(victim);
    }

    @Test
    public void setLife() {
        praetorian.setLife(20d);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(praetorian, "life"), 20d,0d);
    }
}