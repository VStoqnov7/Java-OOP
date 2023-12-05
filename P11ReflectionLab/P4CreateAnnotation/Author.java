package P11ReflectionLab.P4CreateAnnotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PARAMETER})
public @interface Author {

    String name();

}