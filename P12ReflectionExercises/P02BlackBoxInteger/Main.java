package P12ReflectionExercises.P02BlackBoxInteger;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Class<BlackBoxInt> clazz = BlackBoxInt.class;

        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();

        while (!command.equals("END")){
            String[] input = command.split("_");
            int valueToAdd = Integer.parseInt(input[1]);

            switch (input[0]){
                case "add":
                    extracted(clazz, innerValue, blackBoxInt, "add",valueToAdd);
                    break;
                case "subtract":
                    extracted(clazz, innerValue, blackBoxInt, "subtract",valueToAdd);
                    break;
                case "divide":
                    extracted(clazz, innerValue, blackBoxInt, "divide",valueToAdd);
                    break;
                case "multiply":
                    extracted(clazz, innerValue, blackBoxInt, "multiply",valueToAdd);
                    break;
                case "rightShift":
                    extracted(clazz, innerValue, blackBoxInt, "rightShift",valueToAdd);
                    break;
                case "leftShift":
                    extracted(clazz, innerValue, blackBoxInt, "leftShift",valueToAdd);
                    break;
            }

            command = scanner.nextLine();
        }
    }

    private static void extracted(Class<BlackBoxInt> clazz, Field innerValue, BlackBoxInt blackBoxInt, String input,int valueToAdd) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = clazz.getDeclaredMethod(input, int.class);
        method.setAccessible(true);
        method.invoke(blackBoxInt, valueToAdd);
        System.out.println(innerValue.getInt(blackBoxInt));
    }
}