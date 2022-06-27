package com.triple.homework.history.advisor;

import java.lang.annotation.*;

@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SavePointHistory {
}
