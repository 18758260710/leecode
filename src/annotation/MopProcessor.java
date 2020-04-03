package annotation;

import java.lang.reflect.Method;

/**
 * @author wengtao
 * @date 2020/3/30
 **/
public class MopProcessor {
    public void parseMethod(final Class<?> clazz) {
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            final Mop my = method.getAnnotation(Mop.class);
            if (null != my) {
                System.out.println(my.name());
            }
        }
    }
}
