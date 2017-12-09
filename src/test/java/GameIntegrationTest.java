import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Praetorian.class})
public class GameIntegrationTest {

    public static final String VICTIM_IS_ALREADY_DEAD = "Victim is already dead";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Game instance;
    private Praetorian praetorian;
    private Zombie zombie;
    private Wizard wizard;

    @Before
    public void init() {
        PowerMockito.mockStatic(Math.class);
        instance = Game.getInstance();
        praetorian = instance.getPraetorian();
        zombie = instance.getZombie();
        wizard = instance.getWizard();
    }

    @Test
    public void testGame() {
        expectedException.expectMessage(VICTIM_IS_ALREADY_DEAD);
        expectedException.expect(IllegalStateException.class);

        assertLife(150d,30d);

        wizard.hit(praetorian);
        assertLife(144.5, 30d);

        Mockito.when(Math.random()).thenReturn(0.99d);
        praetorian.hit(zombie);
        assertLife(144.5,-16d);

        Mockito.when(Math.random()).thenReturn(0.5d);
        wizard.heal(praetorian);
        assertLife(149.5, -16d);

        praetorian.hit(zombie);
    }

    private void assertLife(double praetorianLife, double zombieLife) {
        assertEquals(praetorian.getLife(), praetorianLife,0.1d);
        assertEquals(zombie.getLife(),zombieLife,0.1d);
    }
}