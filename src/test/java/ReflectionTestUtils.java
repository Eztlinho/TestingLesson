import java.lang.reflect.Field;

/**
 * Created by Steve on 12/9/2017.
 */
public class ReflectionTestUtils {

    private static final ReflectionTestUtils INSTANCE = new ReflectionTestUtils();

    public static ReflectionTestUtils getInstance() {
        return  INSTANCE;
    }

    public void setByReflectionIntoParent(Object object, String name, double value) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }
    public void setByReflection(Object object, String name, int value) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }
    public void setByReflection(Object object, String name, boolean value) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }


    public double getByReflectionFromParent(Object object, String name) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(name);
            field.setAccessible(true);
            return (Double)field.get(object);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }
    public int getByReflection(Object object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return (Integer)field.get(object);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }

    public boolean setByReflection(Object object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return (Boolean) field.get(object);
        }
        catch (Exception e) {
            throw new RuntimeException("Reflection failed");
        }
    }
}
