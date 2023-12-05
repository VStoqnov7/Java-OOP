package P11ReflectionLab.P03HighQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        Class clazz = Reflection.class;

        Field[] fields = clazz.getDeclaredFields();


        Arrays.stream(fields).filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(field -> System.out.printf("%s must be private!%n",field.getName()));

        Method[] getters = clazz.getDeclaredMethods();

        Arrays.stream(getters)
                .filter(method -> method.getName().startsWith("get") && method.getParameterCount() == 0)
                .filter(field -> !Modifier.isPublic(field.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(field -> System.out.printf("%s have to be public!%n",field.getName()));


        Method[] setters = clazz.getDeclaredMethods();


        Arrays.stream(setters)
                .filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1)
                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(field -> System.out.printf("%s have to be private!%n",field.getName()));
    }
}
