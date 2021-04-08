package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Repeatable(RisksContainingAnnotation.class)
@Target({ ElementType.TYPE, ElementType.TYPE_USE })
@Retention(RetentionPolicy.SOURCE)
public @interface RiskAnnotation {

	String danger(); //required param

	int riskLevel() default 2; //optional param

	static final int RISK_MAX_LEVEL = 10;
}