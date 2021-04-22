import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command
{
    public String[] possibleNames() default "no names";
    public String args() default "no args";
    public String description() default "no description";
    public boolean inProgress() default true;
    public boolean takesArgs() default true;
}
