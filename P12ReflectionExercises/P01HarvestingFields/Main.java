package P12ReflectionExercises.P01HarvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Class clazz = RichSoilLand.class;
        String command = scanner.nextLine();

        Map<String, List<StringBuilder>> data = new HashMap<>();
        data.put("private", new ArrayList<>());
        data.put("protected", new ArrayList<>());
        data.put("public", new ArrayList<>());
        data.put("all", new ArrayList<>());

        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        fillData(data, fields);

        while (!command.equals("HARVEST")) {


            switch (command) {
                case "private":
                    data.get("private").forEach(System.out::println);
                    break;
                case "protected":
                    data.get("protected").forEach(System.out::println);
                    break;
                case "public":
                    data.get("public").forEach(System.out::println);
                    break;
                case "all":
                    data.get("all").forEach(System.out::println);
                    break;
            }
            command = scanner.nextLine();
        }


    }

    private static void fillData(Map<String, List<StringBuilder>> data, List<Field> fields) {
        for (Field field : fields) {

            int modifiers = field.getModifiers();
            String access = Modifier.toString(modifiers);
            field.setAccessible(true);

            data.get("all").add(new StringBuilder(access + " " + field.getType().getSimpleName() + " " + field.getName()));

            switch (access) {
                case "private":
                    data.get("private").add(new StringBuilder(access + " " + field.getType().getSimpleName() + " " + field.getName()));
                    break;
                case "protected":
                    data.get("protected").add(new StringBuilder(access + " " + field.getType().getSimpleName() + " " + field.getName()));
                    break;
                case "public":
                    data.get("public").add(new StringBuilder(access + " " + field.getType().getSimpleName() + " " + field.getName()));
                    break;
            }
        }
    }
}
