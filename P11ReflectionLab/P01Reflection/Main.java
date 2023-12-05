package P11ReflectionLab.P01Reflection;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Scanner scanner = new Scanner(System.in);

        Class clazz = Reflection.class;


        System.out.println(clazz.toString());
        System.out.println(clazz.getSuperclass());

        Class[] interfaces = clazz.getInterfaces();

        Arrays.stream(interfaces).forEach(System.out::println);

        Reflection reflection = new Reflection();
        System.out.println(reflection);


        //class java.lang.Object
        //interface java.io.Serializable
        //Name: Java
        //WebAddress: oracle.com
        //email: mail@oracle.com
        //zip: 1407

    }
}