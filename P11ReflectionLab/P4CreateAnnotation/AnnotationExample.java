package P11ReflectionLab.P4CreateAnnotation;

import P11ReflectionLab.P01Reflection.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Author(name = "Georgi")
public class AnnotationExample {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<Reflection> clazz = Reflection.class;

        Method method = clazz.getDeclaredMethod("setName", String.class);

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        Reflection reflection = new Reflection();

        method.setAccessible(true);

        Author myAnnotation = (Author) parameterAnnotations[0][0];

        method.invoke(reflection, myAnnotation.name());

        System.out.println(reflection);

    }
}
