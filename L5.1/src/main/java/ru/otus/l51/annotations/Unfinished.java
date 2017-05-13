package ru.otus.l51.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://en.wikipedia.org/wiki/Java_annotation
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,
        ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE,
        ElementType.PACKAGE,ElementType.FIELD,ElementType.LOCAL_VARIABLE})
@Inherited

public @interface Unfinished {
    public enum Priority { LOW, MEDIUM, HIGH }
    String value();
    String[] changedBy() default "";
    String[] lastChangedBy() default "";
    Priority priority() default Priority.MEDIUM;
    String createdBy() default "James Gosling";
    String lastChanged() default "2011-07-08";
}
