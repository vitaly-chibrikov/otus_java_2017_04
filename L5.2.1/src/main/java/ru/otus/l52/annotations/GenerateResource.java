package ru.otus.l52.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tully.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateResource {
    String file();
    String message();
}
