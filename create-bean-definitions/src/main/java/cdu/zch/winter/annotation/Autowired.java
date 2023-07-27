package cdu.zch.winter.annotation;

import java.lang.annotation.*;

/**
 * @author Zch
 * @date 2023/7/27
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    /**
     * isRequired
     */
    boolean value() default true;

    /**
     * Bean name if set
     */
    String name() default "";

}
