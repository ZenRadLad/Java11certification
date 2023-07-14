package collections;

import java.io.IOException;
import java.security.BasicPermission;
import java.security.Permission;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Generics {
	public static void main(String[] args) {
		// Naming conventions :
			// E = element
			// K = map key
			// V = map value
			// N = number
			// T = generic data type
			// S,U,V = multiple generic types
		//TODO : 
		    // review wild cards and do Q&As
			// Generic wildcards and generic paramaters in methods
			// Valid lambda expressions
		methodReferences();
		classes();
		interfaces();
		methods();
	}

	private static void methodReferences() {
		// using lambdas Vs using ::
		Consumer<List<Integer>> sortConsumer = i -> Collections.sort(i);
		Consumer<List<Integer>> sortConsumerRef = Collections::sort;

		final var str = "test";
		Predicate<String> predicate = s -> str.startsWith(s);
		Predicate<String> predicateRef = str::startsWith;

		BiPredicate<String, String> biPredicate = (s, p) -> s.startsWith(p);
		BiPredicate<String, String> biPredicateRef = String::startsWith;

		Supplier<List<String>> supplierOfStrings = () -> new ArrayList();
		Supplier<List<String>> supplierOfStringsRef = ArrayList::new;

		Function<Integer, List<String>> lambdaFunction = x -> new ArrayList(x);
		Function<Integer, List<String>> lambdaFunctionRef = ArrayList::new;
	}

	private static void classes() {
		class GenericClass<T> {

			public T apply(T o) {
				return o;
			}
		}

		GenericClass<String> myG = new GenericClass<>();
		String result = myG.apply("Test");
		System.out.println("Generic class method : " + result);

		class GenericClassWithTwoTypes<T, N> {

			private T contents;
			private N size;

			public GenericClassWithTwoTypes(T o, N s) {
				this.contents = o;
				this.size = s;
			}

			@Override
			public String toString() {
				return this.contents + " of size : " + this.size;
			}
		}

		GenericClassWithTwoTypes<String, Integer> myTwoG = new GenericClassWithTwoTypes<>("Test Generic Object", 2);
		System.out.println("Generic class method : " + myTwoG.toString());
	}

	public interface Shippable<T> {
		void ship(T t);
	}

	public class Electronics {
		public String productType;

		Electronics(String p) {
			this.productType = p;
		}
	}

	private static void interfaces() {
		// use generics interface and implementing class
		class Shipping implements Shippable<Electronics> {

			@Override
			public void ship(Electronics t) {

			}
		}

		// another way
		class ShippingAbstract<U> implements Shippable<U> {
			public void ship(U t) {

			}
		}

	}

	private static void methods() {
		class Generic<T> {

		}
		class Handler {
			public <T> void prepare(T t) {

			}

			public <T> Generic<T> ship(T t) {
				return new Generic<T>();
			}
		}
	}

	private static void wildcards() {

		// Unknown List<?>
		List<?> unkownTypeList = new ArrayList<Integer>();
		
		// extends = List<? extends MyClass> (higher bound)
				// List of objects that are instances of MyClass or MyIface
		List<? extends Number> extendsTypeList = new ArrayList<Integer>();
		List<? extends Exception> extendsTypeList2 = new ArrayList<IOException>();
		List<? extends Exception> extendsTypeList3 = new ArrayList<RuntimeException>();
		List<? extends RuntimeException> extendsTypeList4 = new ArrayList<IllegalArgumentException>();
		
		//Doesn't compile because RuntimeException extends Objects not the inverse
		//List<? extends NullPointerException> extendsTypeList5 = new ArrayList<RuntimeException>();
		
		// super = List<? super MyClass> (lower bound)
				//List is of type Class or a super class of MyClass or MyIface
		List<? super IllegalArgumentException> superTypeList = new ArrayList<RuntimeException>();
		List<? super Double> superTypeList2 = new ArrayList<Number>();
		List<? super LocalDateTime> superTypeList3 = new ArrayList<Temporal>();
		List<? super Integer> superTypeList4 = new ArrayList<Object>();
		List<? super BasicPermission> superTypeList5 = new ArrayList<Permission>();
	}
}
