package P11ReflectionLab.Demo;


import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {


//      Class<?> clazz = Class.forName("P11ReflectionLab.Demo");
        Class clazz = Reflection.class;
//        List<String> string = Files.readAllLines(Paths.get("P11ReflectionLab//Demo"));   /// todo


        System.out.println(clazz.toString());
        //class P11ReflectionLab.Demo.Reflection


        Class<?> superClass = clazz.getSuperclass();
        System.out.println(superClass);
        //class java.lang.Object


        Class<?>[] interfaces = clazz.getInterfaces();
        Arrays.stream(interfaces).forEach(System.out::println);
        //interface java.io.Serializable
        Class<?>[] interfaces1 = clazz.getSuperclass().getInterfaces();
        Arrays.stream(interfaces1).forEach(System.out::println);
        //interface P11ReflectionLab.Demo.AnimalInterfaces



        Reflection reflection = (Reflection) clazz.getDeclaredConstructor().newInstance();
        System.out.println(reflection);
        //Name: Java
        //WebAddress: oracle.com
        //email: mail@oracle.com
        //zip: 1407

        Constructor<?>[] declaredConstructorS = clazz.getDeclaredConstructors();
        Arrays.stream(declaredConstructorS).forEach(System.out::println);
        //protected P11ReflectionLab.Demo.Reflection(java.lang.String,java.lang.String,java.lang.String,int)
        //private P11ReflectionLab.Demo.Reflection(java.lang.String,java.lang.String,java.lang.String)
        //public P11ReflectionLab.Demo.Reflection()

        Constructor<?>[] publicConstructorS = clazz.getConstructors();
        Arrays.stream(publicConstructorS).forEach(System.out::println);
        //public P11ReflectionLab.Demo.Reflection()

        Constructor<Reflection> constructor2 = clazz.getDeclaredConstructor(String.class,String.class,String.class);
        System.out.println(constructor2);
        //private P11ReflectionLab.Demo.Reflection(java.lang.String,java.lang.String,java.lang.String)
        Constructor<Reflection> constructor3 = clazz.getConstructor(String.class, String.class, String.class);
        System.out.println(constructor3);
        //Exception why we don't have this constructor or this constructor is private !!!




        Field[] publicFields = clazz.getFields();
        Arrays.stream(publicFields).forEach(System.out::println);
        //public java.lang.String P11ReflectionLab.Demo.Reflection.name

        Field[] allFields = clazz.getDeclaredFields();
        Arrays.stream(allFields).forEach(System.out::println);
        //private static final java.lang.String P11ReflectionLab.Demo.Reflection.nickName
        //public java.lang.String P11ReflectionLab.Demo.Reflection.name
        //protected java.lang.String P11ReflectionLab.Demo.Reflection.webAddress
        //java.lang.String P11ReflectionLab.Demo.Reflection.email
        //private int P11ReflectionLab.Demo.Reflection.zip


        Field publicField = clazz.getField("name");
        Field allField = clazz.getDeclaredField("name");
        System.out.println(publicField);
        //public java.lang.String P11ReflectionLab.Demo.Reflection.name
        System.out.println(publicField.getName());
        //name
        System.out.println(publicField.getType());
        //class java.lang.String


        Reflection reflections = new Reflection();
        Field zipField = clazz.getDeclaredField("zip");
        zipField.setAccessible(true);
        int zipCode = (int) zipField.get(reflections);
        System.out.println(zipCode);
        //1407
        zipField.set(reflections,1243532);
        zipCode = (int) zipField.get(reflections);
        System.out.println(zipCode);
        //1243532




        Method[] publicMethods = clazz.getMethods();
        Arrays.stream(publicMethods).forEach(System.out::println);
        //public final java.lang.String P11ReflectionLab.Demo.Reflection.getName()
        //public java.lang.String P11ReflectionLab.Demo.Reflection.toString()
        //public java.lang.String P11ReflectionLab.Demo.Reflection.getEmail()
        //public void P11ReflectionLab.Demo.Reflection.setEmail(java.lang.String)
        //public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
        //public final void java.lang.Object.wait() throws java.lang.InterruptedException
        //public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
        //public boolean java.lang.Object.equals(java.lang.Object)
        //public native int java.lang.Object.hashCode()
        //public final native java.lang.Class java.lang.Object.getClass()
        //public final native void java.lang.Object.notify()
        //public final native void java.lang.Object.notifyAll()
        Method[] allMethods = clazz.getDeclaredMethods();
        Arrays.stream(allMethods).forEach(System.out::println);
        //public final java.lang.String P11ReflectionLab.Demo.Reflection.getName()
        //public java.lang.String P11ReflectionLab.Demo.Reflection.toString()
        //private void P11ReflectionLab.Demo.Reflection.setName(java.lang.String)
        //private void P11ReflectionLab.Demo.Reflection.setZip(int)
        //private void P11ReflectionLab.Demo.Reflection.setWebAddress(java.lang.String)
        //public void P11ReflectionLab.Demo.Reflection.setEmail(java.lang.String)
        //protected final int P11ReflectionLab.Demo.Reflection.getZip()
        //protected java.lang.String P11ReflectionLab.Demo.Reflection.getWebAddress()
        //public java.lang.String P11ReflectionLab.Demo.Reflection.getEmail()
        Method doSomething = clazz.getDeclaredMethod("setZip",int.class);
        System.out.println(doSomething);
        //private void P11ReflectionLab.Demo.Reflection.setZip(int)


        int modifiers = clazz.getModifiers();
        System.out.println(Modifier.isPublic(modifiers));
        System.out.println(Modifier.isPrivate(modifiers));
        System.out.println(Modifier.isFinal(modifiers));
        //true
        //false
        //false
    }
}
