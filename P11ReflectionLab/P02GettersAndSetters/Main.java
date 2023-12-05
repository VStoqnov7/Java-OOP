package P11ReflectionLab.P02GettersAndSetters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Class clazz = Reflection.class;

        Method[] methods = clazz.getDeclaredMethods();

        Function<Class<?>, String> formatType = c -> c == int.class ? "class int" : c.toString();

        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.printf("%s will return %s%n", m.getName(),
                        formatType.apply(m.getReturnType())));

        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.printf("%s and will set field of %s%n", m.getName(),formatType.apply(m.getParameterTypes()[0])));
    }
}
