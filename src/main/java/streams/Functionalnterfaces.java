package streams;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Functionalnterfaces {

	public static void main(String[] args) {
		lambdas();
		methodReferences();
		functionalIntefaces();
		//TODO : 
			// implement functionalIfaces using lambda expressions including ifaces from java.util.function API

	}

	private static void lambdas() {
		//https://philvarner.github.io/pages/modern-java.html
		//		() -> "Hello"
		
		//		() -> System.out.println("Hello")
		
		//		(String str) -> str.length()
		
		//		(str) -> str.length()
		
		//		str -> str.length()
		
		//		(int i, int j) -> i + j
		
		//		(i, j) -> i + j
		
		//		() -> {
		//		    System.out.println("Hello");
		//		    System.out.println("World");
		//		}
		
		//		(int i) -> {
		//		    System.out.println("Hello");
		//		    return i;
		//		}
	}

	private static void methodReferences() {
		// Static methods
		Supplier<Thread> runtimeSup = Thread::currentThread;

		// Bound instance methods
		Supplier<String> helloSup = "hello"::toUpperCase;
		Consumer<String> printer = System.out::println;

		// Unbound instance methods
		Function<String, String> lower = String::toLowerCase;

		// Constructors
		Supplier<String> stringSup = String::new;
		Function<Integer, int[]> arrSup = int[]::new;
	}
	
	private static void functionalIntefaces() {
		// Implement functional interfaces using lambda expressions
		// including interfaces from the java.util.function package
		
		// Functional Interfaces :
			// have only one abstract method
			// doesn't count :
				// static and default methods 
				// methods with the same signature as public methods in java.lang
			// @FunctionalInterfaces is not required
		
		// Function<T,R>        -> R apply(T value)
			// transform values
		// UnaryOperator<T>     -> T apply(T value) same as function but its I/O have the same type
		// BinaryOperator<T>    -> T apply(T v1, Tv2) two inputs and output have the same type
		// BiFunction<T, U, R>  -> R apply(T v1, U v2) same as function but its I/O have the same type
		// Consumer<T>  	    -> void accept(T value) => input without output
			// last operation on a sequence of ops 
		// BiConsumer<T, U>     -> void accept(T v1, U v2)
		// Supplier<T>    	    -> get(T) => zero input (T = type) that returns one output
			// generates value 
		// Predicate<T>         -> boolean test(T value)
			// checks if input matches a condition
		// BiPredicate<T, U>    -> boolean test(T v1, U v2)
		
		// Custom Functional Interface
		MyFuncIface removeLast = (String s) -> s.substring(0, s.length() - 1);
		String a = removeLast.myMethod("Einsteiny");
		System.out.println("My Functional interface remove last  : " + a);
		
		// Predicate : evalute one argument by using a test method and returns a boolean
				// interface Predicate<T> { boolean test(T t) }
		Predicate<Integer> p = (i) -> i > 20;
		System.out.println("Predicate ( 19 > 20) : " + p.test(19));
		
		Predicate<Integer> p1 = (i) -> ((i instanceof Integer) && i == 19);
		System.out.println("Predicate (i instanceof Integer) : " + p1.test(19));

		// Consumer :
				// accepts single arg with no return value
				// interface Consumer<T> { boolean accept(T t) }
		Consumer<String> consumerStr = s -> System.out.println(s.toLowerCase());
		consumerStr.accept("NOW LOWERCASED ! ");

		// Supplier : supplies a value without an input
			// interface Supplier<T> { T get() }
		Supplier<LocalDateTime> dateTimeSupplier = LocalDateTime::now; // same as () -> LocalDateTime.now()
		System.out.println("LocalDateTime Supplier : " + dateTimeSupplier.get());
		

		// Function : (Consumer + Supplier ) transforms a value from one type to another
			// interface Function<T, R> { R apply(T t) }
		Function<Integer, String> integerToStringFunc = (Integer i) -> Integer.toString(i);
		System.out.println("Function<Integer, String>  integerToString : " + integerToStringFunc.apply(77));

		// BiFunction : represents a function that accepts two args and produce a result
			// interface BiFunction<T, U, R>
		BiFunction<Double, Double, Double> sum = (d1,d2) ->  d1 + d2;
		System.out.println("BiFunction<Double, Double, Double> sum.apply(1.99, 99.11) : " + sum.apply(1.99, 99.11));

		// BinaryOperator :
			// takes two arguments and returns one
		BinaryOperator<String> binaryOp = (s1, s2) -> s1.concat(s2);
		System.out.println("Concat 'Yo ' and 'Wassaup' using BinaryOperator: " + binaryOp.apply("Yo ", "Wassup"));

		// UnaryOperator :
			// Function by input and output have the same types
		UnaryOperator<String> uO = (str) ->  "J".concat(str);
		System.out.println("Append 'J' to 'VM' :  " + uO.apply("VM"));
		
		// Functional Composition = combine multiple functions into one in different ways
		
			//Predicate and Predicate
		Predicate<Integer> twoPreds = p.and(p1);
		System.out.println("Predicate.and(Predicate) false.and(true) : " + twoPreds.test(19));
		
			// Function andThen Function
			// .compose() is the same as andThen() except the order of the functions is reversed
	        // Compose a new function out of the two functions 
		BiFunction<String, String, String> andThen = binaryOp.andThen(uO);
		System.out.println("Function.and(Function) String appending : " + andThen.apply("First", "Sec"));
		
		// java.util.function : Specialized Type FunctionalInterfaces to improve performance
			// XFunction<R>, XPredicate, XSupplier, XConsumer, XToYFunction
			// ToXFunction<T>, ObjXConsumer<T>, XUnaryOperator, XBinaryOperator
			// ToXBiFunction<T,U>
			// ===> where X,Y = Int, Long, Double and BooleanSupplier
	}

	@FunctionalInterface 
	interface MyFuncIface {
		String myMethod(String s);
	}
}