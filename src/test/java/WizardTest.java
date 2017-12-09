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

/**
 * Created by Steve on 12/9/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Wizard.class})
public class WizardTest {

    public Wizard wizard = new Wizard();
    public static final ReflectionTestUtils REFLECTION_TEST_UTILS = ReflectionTestUtils.getInstance();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private Human injuredHuman;

    @Before
    public void init() {
        PowerMockito.mockStatic(Math.class);
        Mockito.when(Math.random()).thenReturn(0.5d);
    }

    @Test
    public void defaultConstructor() {
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(wizard,"force"), 5, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(wizard,"level"), 1, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflectionFromParent(wizard,"life"), 100, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflection(wizard,"elixir"), 10, 0.1);
        Assert.assertEquals(REFLECTION_TEST_UTILS.getByReflection(wizard,"defaultHealPower"), 10, 0.1);
    }
    
    @Test
    public void heal_victim() {
        Mockito.when(injuredHuman.getLife()).thenReturn(10d);
        REFLECTION_TEST_UTILS.setByReflection(wizard, "elixir",20);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"hasFailed", false);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(wizard, "level",2);
        REFLECTION_TEST_UTILS.setByReflection(wizard, "defaultHealPower",50);

        wizard.heal(injuredHuman);

        Mockito.verify(injuredHuman).setLife(60d);
    }
    @Test
    public void heal_victimFails() {
        Mockito.when(injuredHuman.getLife()).thenReturn(10d);
        Mockito.when(Math.random()).thenReturn(0.96d);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"elixir", 2);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"hasFailed", false);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(wizard,"level", 4);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"defaultHealPower", 150);

        wizard.heal(injuredHuman);

        Mockito.verifyZeroInteractions(injuredHuman);
        Assert.assertEquals(REFLECTION_TEST_UTILS.setByReflection(wizard,"hasFailed"), true);
    }
    @Test
    public void heal_victimFailsTwice() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("I QUIT !");

        Mockito.when(injuredHuman.getLife()).thenReturn(10d);
        Mockito.when(Math.random()).thenReturn(0.96d);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"elixir", 2);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"hasFailed", true);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(wizard,"level", 4);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"defaultHealPower", 150);

        wizard.heal(injuredHuman);

        Mockito.verifyZeroInteractions(injuredHuman);
    }

    @Test
    public void getHealPower_hasEnoughElixir() {
        REFLECTION_TEST_UTILS.setByReflection(wizard,"elixir", 2);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(wizard,"level", 4);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"defaultHealPower", 150);

        Assert.assertEquals(wizard.getHealPower(), 300,0);
    }

    @Test
    public void getHealPower_hasNotEnoughElixir() {
        REFLECTION_TEST_UTILS.setByReflection(wizard,"elixir", 1);
        REFLECTION_TEST_UTILS.setByReflectionIntoParent(wizard,"level", 4);
        REFLECTION_TEST_UTILS.setByReflection(wizard,"defaultHealPower", 150);

        Assert.assertEquals(wizard.getHealPower(), 0,0);
    }

    @Test
    public void elixirIs48_return48() {
        REFLECTION_TEST_UTILS.setByReflection(wizard,"elixir", 48);
        Assert.assertEquals(wizard.getElixir(), 48);
    }

}