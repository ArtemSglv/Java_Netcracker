package injection;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private static String path_to_prop = "src/main/resources/injection.properties";

    public static <T> T inject(T object){
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(path_to_prop);
            property.load(fis);

            Class<?> target_class=object.getClass();
            Field[] fields = target_class.getDeclaredFields();
            for(Field f:fields) {
                if(f.isAnnotationPresent(Inject.class)) {
                    String sort_type_value = property.getProperty(f.getType().getName());
                    Object sort_instance = Class.forName(sort_type_value).newInstance();

                    boolean field_accessible = f.isAccessible();
                    f.setAccessible(true);
                    f.set(object,sort_instance);
                    f.setAccessible(field_accessible);
                }
            }

        } catch (Exception e) {
            System.err.println("ОШИБКА!");
        }

        return object;
    }
}
