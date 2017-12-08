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

import java.lang.reflect.Field;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Praetorian.class})
public class PraetorianTest {
    public static final String VICTIM_IS_ALREADY_DEAD = "Victim is already dead";

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
    public void getHitForce_15ForceAndLevel1_165() {
        Assert.assertEquals(praetorian.getHitForce(), 16.5, 0.1);
    }

    @Test
    public void getLife_isEqualsTo_150() {
        Assert.assertEquals(praetorian.getLife(), 150, 1);
    }

    @Test
    public void getHitForce_20ForceAndLevel3() {
        setForceByReflection(20);
        setLevelByReflection(3);
        Assert.assertEquals(praetorian.getHitForce(), 26.0, 0.1);
    }

    @Test
    public void getHitForce_RandomHigherThan95_CriticalForce() {
        Mockito.when(Math.random()).thenReturn(0.96d);
        setForceByReflection(20);
        setLevelByReflection(3);
        Assert.assertEquals(praetorian.getHitForce(), 63.0, 0.1);
    }

    @Test
    public void hit_setVictimsLife() {
        Mockito.when(victim.getLife()).thenReturn(180d);
        setForceByReflection(20);
        setLevelByReflection(3);

        praetorian.hit(victim);

        Mockito.verify(victim).setLife(154d);
    }

    @Test
    public void hit_victimAlreadyDead_ThrowException() {
        expectedException.expectMessage(VICTIM_IS_ALREADY_DEAD);
        expectedException.expect(IllegalStateException.class);

        Mockito.when(victim.getLife()).thenReturn(0d);
        setForceByReflection(20);
        setLevelByReflection(3);

        praetorian.hit(victim);
    }

    @Test
    public void setLife() {
        praetorian.setLife(20d);

        Assert.assertEquals(getLifeByReflection(), 20d,0d);
    }

    private void setForceByReflection(double force) {
        try {
            Field field = praetorian.getClass().getSuperclass().getDeclaredField("force");
            field.setAccessible(true);
            field.set(praetorian, force);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }

    private double getLifeByReflection() {
        try {
            Field field = praetorian.getClass().getSuperclass().getDeclaredField("life");
            field.setAccessible(true);
            return (Double)field.get(praetorian);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }

    private void setLevelByReflection(double level) {
        try {
            Field field = praetorian.getClass().getSuperclass().getDeclaredField("level");
            field.setAccessible(true);
            field.set(praetorian, level);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }
}