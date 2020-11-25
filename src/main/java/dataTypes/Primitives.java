package dataTypes;

public class Primitives {

	public static void main(String[] args) {
		// https://docs.oracle.com/javase/tutorial/java%nutsandbolts/datatypes.html
		primitives();
		wrappers();
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
		byte maxB = 127;
		byte minB = -128;
		System.out.printf("byte min value : %d, max : %d%n", maxB, minB);
		
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
		char myCh = 'a'; // unicode chars
		char code = 122;
		char hexUnicode = '\u0247';
		System.out.printf("char single : %s, code : %s, unicode : %s%n", myCh, code, hexUnicode);

		boolean bool = null != null; // true || false
		System.out.printf("boolean : is null != null = %s%n%n", bool);
		System.out.print("-----------------------------------------------------");
	    System.out.println("");
	    System.out.println("");
	}

	
	// Wrapper Classes
	public static void wrappers() {
		// Numberic
		Byte byteVal = Byte.valueOf("9");
		System.out.printf("Byte wrapper => Byte.valueOf(\"9\") byteValue() : %d%n", byteVal.byteValue());
		
		Short myShort = 89;
		System.out.printf("Short wrapper => Short myShort = 89 shortValue() : %d%n", myShort.shortValue());

		Integer myInt = Integer.valueOf(Integer.MAX_VALUE);
		System.out.printf("Integer wrapper => Integer.valueOf(Integer.MAX_VALUE) longValue() : %d%n", myInt.intValue());

		Long myLong = Long.valueOf(20l);
		System.out.printf("Long wrapper => Long.valueOf(20l) : %d%n", myLong.longValue());

		Float myFloat = 699.19f;
		System.out.printf("Float wrapper => Float myFloat = 699.19f  floatValue(): %f%n", myFloat.floatValue());

		Double myDouble = 699.19;
		System.out.printf("Double wrapper => Double myDouble = 699.19 doubleValue() : %f%n", myDouble.doubleValue());

		// Textual
		Boolean myBool = Boolean.valueOf("true");
		System.out.printf("Boolean wrapper => Boolean myBool = Boolean.valueOf(\"true\") booleanValue() : %s%n", myBool.booleanValue());
		
		Character myChar = 'A';
		System.out.printf("Character wrapper => Character myChar = 'A' charValue() : %s%n", myChar.charValue());
		
		//AutoBoxing
		Double autoboxDouble = 99.99;
		System.out.printf("Autoboxing 99.99 literal value in an Double object : %f%n", autoboxDouble.doubleValue());
		
		//Caching
		Float floatObj = 11f;
		Float sameCachedFloatObj = Float.valueOf(11f);
		System.out.printf("Autoboxing of values from -128 to 127 "
				+ "true and false , and '\\u0000' to '\\u007f' will result in cached "
				+ "objects (refering to the same object) %n" 
				+ "   ==> Float.valueOf(11f) and Float floatObj = 11f hashcodes : (%d == %s) %n", floatObj.hashCode(), sameCachedFloatObj.hashCode() );

		//Unboxing
		Integer unboxInteger = Integer.valueOf(2);
		int unboxedInt = unboxInteger;
		System.out.printf("Unboxing Integer unboxInteger = Integer.valueOf(2) to int unboxedInt = unboxInteger : %s%n", unboxedInt);
	}
	
	// Casting

	// Operators

	// Parenthesis

	// Type promotion
}