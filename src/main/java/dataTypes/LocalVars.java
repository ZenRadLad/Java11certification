package datatypes;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LocalVars {

	// var invalidVar = 9; var can't be used for instance members
	// var static invalidStaticVar = 1; var can't be used for class members

	public static void main(String[] args) {
		//any use of 'var' outside of a local scope (i.e. methods, constructors, or
		//initializer blocks) is invalid. Even within a local scope, it is valid only
		//if there is a value with a known type that is being assigned to the target variable.
		localVarsTypeInference();
		localVarsTypeInferenceInLambdas();
	}

	public static void localVarsTypeInference() {
		// var uninitVar; //can't be uninitilized
		// var nullVar = null; //can't be null (type can't be infered)
		var s = new String("varInference");
		var d = Double.valueOf(17.7);
		System.out.println("var s = new String('varInference') : " + (s.getClass().getName()));
		System.out.println("var d = 17.7 : " + (d.getClass().getName()));

	}
	
	public static void localVarsTypeInferenceInLambdas() {
		//(a) -> a % 2 == 0; even numbers
		var intArr = new Integer[]{1,2,3,4,5,6,7,8,9,10};
		var evenCount = Arrays.stream(intArr).filter((var n) -> n % 2 == 0).collect(Collectors.toList());
		System.out.println("get even numbers from list '1-10' using filter and lambda : " + evenCount);
	}
}