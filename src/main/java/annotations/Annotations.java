package annotations;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

@RiskAnnotation(danger="none")
@RiskAnnotation(danger="possible", riskLevel=7)
public class Annotations {

	public static void main(String[] args) {
	/*
		A form of metadata that have no direct effect on the code they annotate
		
		@Retention(RetentionPolicy.SOURCE)
			-SOURCE : retained at the source code but discarded by the compiler (detec errors, suppress warnings)
			-CLASS : retained by the compiler but ignored by the JVM (generate code, xml files..) => Default retention policy
			-RUNTIME : retained by the JVM and readable at rutime

		Annotation Targets @Target({ ElementType.TYPE, ElementType.TYPE_USE })
			-TYPE (class, interface, enum, annotations)
			-CONSTRUCTOR
			-FIELD (instance and static variables, enum values)
			-LOCAL_VARIABLE
			-METHOD
			-MODULE
			-PACKAGE
			-PARAMETER (constructor, method and lambda params
			-TYPE_PARAMETER (parametrized types, generic declaration => default target )
			-TYPE_USE (on any java type => default target)
			
			@Documented : include comments and annotations in generated javadoc
			
			@Inherited : When applied to a class a subclass will inherit parent annotation information
			
			@Repeatable(RisksContainingAnnotation.class) : when we want to apply the same annotation on a type more than once
	*/
		
		dynamicAnnotationDisovery();
		commonJavaAnnotations();
	}

	private static void dynamicAnnotationDisovery() {
			// Java Reflection API allows dynamic discovery of class structures including annotations
		
			//Get all annotations
			Annotation[] myAnnotations = RiskAnnotation.class.getAnnotations();
			Stream.of(myAnnotations).forEach(a -> System.out.println("Annotation : " + a));
			
			//Get first class annotation type
			Class annotationType = RiskAnnotation.class.getAnnotations()[1].annotationType();
			System.out.println("AnnotationType : " + annotationType);
			
			//Get annotation by type 
			RiskAnnotation[] riskAnnotations = RiskAnnotation.class.getAnnotationsByType(RiskAnnotation.class);
			Stream.of(riskAnnotations).forEach(a -> System.out.println("annotations by type : " + a));
	}
	
	private static void commonJavaAnnotations() {
		/* @Override 
		 * @FunctionalInterface 
		 * @Deprecated(since=javaVersion || forRemoval=boolean)
		 * @SupressWarnings("deprecation" || "unchecked") = ignore compiler warnings related to types/methods || raw types
		 * @SafeVarArgs	indicated that a method doesn't perform any unsafe ops on its varargs params, makes compiler ignore type safety warns	 * */
	}
}