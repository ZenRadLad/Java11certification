package dataTypes;

public class Primitives {

	public static void main(String[] args) {
		// https://docs.oracle.com/javase/tutorial/java%nutsandbolts/datatypes.html
		primitives();
	}

	// Primitives
	/*
	 * byte: 8-bit. usage = saving memory in large arrays, where the memory savings actually matters.
	 *        used in place of int where their limits help to clarify your code the fact
	 *        that a variable's range is limited can serve as a form of documentation.
	 * 
	 * short: 16-bit. usage = saving memory in large arrays.
	 * 
	 * int: 32-bit 
	 * 
	 * long: 64-bit. Use when you need a range of values wider than those provided by int.
	 * 
	 * float: single-precision 32-bit. use a float (instead of double) if you need to save memory
	 *        in large arrays of floating point numbers. It should never be used for precise values, such as currency. 
	 *        For that, you will need to use the java.math.BigDecimal class instead. 
	 * 
	 * double: 64-bit. For decimal values, this data type is generally the default choice.
	 *         It should never be used for precise values, such as currency.
	 * 
	 * boolean: true and false. Use this data type for simple flags that track
	 *          true/false conditions. represents one bit of information, but its "size"
	 *           isn't something that's precisely defined.
	 * 
	 * char: single 16-bit Unicode character. It has a minimum value of '\u0000' (or 0) 
	 *       and a maximum value of '\uffff' (or 65,535 inclusive).
	 */

	public static void primitives() {
		// Numberic
		short minShort = 32767;
		short maxShort = -32767;
		System.out.printf("short min value : %d, max : %d%n", minShort, maxShort);

		// int > long > float > double
		int minInt = Integer.MIN_VALUE;
		int maxInt = Integer.MAX_VALUE;
		System.out.printf("int min value : %d, max : %d%n", minInt, maxInt);

		long minLong = Long.MIN_VALUE;
		long maxLong = Long.MAX_VALUE;
		System.out.printf("long min value : %d, max : %d%n", minLong, maxLong);

		float minFloat = Float.MIN_VALUE;
		float maxFloat = Float.MAX_VALUE;
		System.out.printf("float min value : %f, max : %f%n", minFloat, maxFloat);

		double minDouble = Double.MIN_VALUE;
		double maxDouble = Double.MAX_VALUE;
		System.out.printf("double min value : %f, max : %f%n", minDouble, maxDouble);

		// Textual
		byte maxB = 127;
		byte minB = -128;
		System.out.printf("byte min value : %d, max : %d%n", maxB, minB);

		char myCh = 'a'; // unicode chars
		char code = 122;
		char hexUnicode = '\u0247';
		System.out.printf("char single : %s, code : %s, unicode : %s%n", myCh, code, hexUnicode);

		boolean bool = null != null; // true || false
		System.out.printf("boolean : is null != null = %s%n", bool);
	}

	// Casting
	
	// Wrapper Classes

	// Operators

	// Parenthesis

	// Type promotion
}