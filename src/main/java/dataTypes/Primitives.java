package dataTypes;

public class Primitives {

	static byte b;
    static char c;
    static short s;
    static int i;
    static long l;
    static float f;
    static double d;
    static String str;
    static Object obj;
    
	public static void main(String[] args) {
 		primitives();
 		defaultValues();
 		wrappers();
 		casting();
		operators();
		typePromotion();
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
		System.out.printf("boolean : is null != null = %s", bool);
		newLines();
	}

	private static void defaultValues() {
		System.out.println("default value of byte : " + b);
        System.out.println("default value of char : " + c);
        System.out.println("default value of short : " + s);
        System.out.println("default value of int : " + i);
        System.out.println("default value of long : " + l);
        System.out.println("default value of float : " + f);
        System.out.println("default value of double : " + d);
        System.out.println("default value of String : " + str);
        System.out.println("default value of Object : " + obj);
        newLines();
	}
	
	// Wrapper Classes
	public static void wrappers() {
		// Numberic
		Byte byteVal = Byte.valueOf("9");
		System.out.printf("Byte wrapper => Byte.valueOf(\"9\") byteValue() : %d%n", byteVal);
		
		Short myShort = 89;
		System.out.printf("Short wrapper => Short myShort = 89 shortValue() : %d%n", myShort);

		Integer myInt = Integer.valueOf(Integer.MAX_VALUE);
		System.out.printf("Integer wrapper => Integer.valueOf(Integer.MAX_VALUE) longValue() : %d%n", myInt);

		Long myLong = Long.valueOf(20l);
		System.out.printf("Long wrapper => Long.valueOf(20l) : %d%n", myLong);

		Float myFloat = 699.19f;
		System.out.printf("Float wrapper => Float myFloat = 699.19f  floatValue(): %f%n", myFloat);

		Double myDouble = 699.19;
		System.out.printf("Double wrapper => Double myDouble = 699.19 doubleValue() : %f%n", myDouble);

		// Textual
		Boolean myBool = Boolean.valueOf("true");
		System.out.printf("Boolean wrapper => Boolean myBool = Boolean.valueOf(\"true\") booleanValue() : %s%n", myBool);
		
		Character myChar = 'A';
		System.out.printf("Character wrapper => Character myChar = 'A' charValue() : %s%n", myChar);
		
		//AutoBoxing
		Double autoboxDouble = 99.99;
		System.out.printf("Autoboxing 99.99 literal value in an Double object : %f%n", autoboxDouble);
		
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
		newLines();
	}
	
	// Casting
	public static void casting() {
		
		//Widening Casting (automatically) - converting a smaller type to a larger type size
				//byte -> short -> char -> int -> long -> float -> double
		
		//Narrowing Casting (manually) - converting a larger type to a smaller size type
			   //double -> float -> long -> int -> char -> short -> byte
		
		//Implicit/Auto
		int i = 81;
		double myD = i;
		System.out.printf("Implicit Casting double from i=81 : %f%n", myD);
		
		//Explicit/Manual
		double myDo = 81.99;
		int i2 = (int) myDo;
		System.out.printf("Explicit Casting int from double=81.99 : %d%n", i2);
		
		//Other Explicit/Manual casting examples
		int i1 = 18;
		char c = (char) i1;
		byte b = (byte) i1;
		System.out.printf("Explicit Casting int 10 to char : %s%n", c);
		System.out.printf("Explicit Casting int 10 to byte : %s%n", b);

		double d = 7.99;
		float f = (float) d;
		System.out.printf("Explicit Casting 7.99 double to (float) 7.99 : %f%n", f);
		
		int maxInt = Integer.MAX_VALUE;
		float myFloat = maxInt;
		System.out.printf("Implicit widdening of int Integer.MAX_VALUE to float : %f%n", myFloat);
		
		//maxInt = myFmyFloat; float narrowing to int donesn't compile
		
		long maxLong = Long.MAX_VALUE;
		double myDouble = maxLong;
		System.out.printf("Implicit widdening of long Long.MAX_VALUE to double : %f%n", myDouble);
		
		//maxLong = myDouble; long narrowing to double donesn't compile		
		
		int maxByteInt = 128;
		byte spillageByte = (byte) maxByteInt;
		System.out.printf("Explicit Casting int 128 to byte(max 127) : %s !%n", spillageByte);

		char c1 = '\u0061'; //unicode for 'a'
		short s1 = (short) c1;
		System.out.printf("Casting char '\u0061' to short (max 127) : %s%n", s1);	
		newLines();
	}
	
	
	public static void operators() {

		int modulus = 3 % 2;
		System.out.println("Modulus/remainder operator 3 % 2 : " +  modulus);	
		
		System.out.println("Operator prcedence : * > / > % > + > - > =");	

		int inc = 1;
		int preInc = ++inc; 
		int jinc = 1;
		int postInc = jinc++;
		
		System.out.printf("Pre/Instant increment ++1 : %d, Post/Late Increment 1++ : %d %n", preInc, postInc);
		
		//Bitwise operators
		int i = 7;
		int j = 10;
		
		System.out.printf("Bitwise AND '&' operator (7 & 10) : %s, Bitwise OR (inclusive OR) '|' operator (7 | 10): %s %n", (i&j), (i|j));
		System.out.printf("Bitwise XOR (exclusive OR) '^' operator (Sets each bit to 1 if one of the two bits is 1 = (7^10)) :  %d %n",  (i^j));
		System.out.printf("Bitwise NOT '~' operator (Inverts all the bits = (~7)) :  %d %n",  (~i));
		
		System.out.printf("Right shift '>>' operator (Shifts the bits to the right and fills 0 on voids left as a result = (7>>10)) :  %d %n",  (i>>j));
		System.out.printf("Left shift '<<' operator (Shifts the bits to the left and fills 0 on voids left as a result  = (7<<10)) :  %d %n",  (i<<j));
		System.out.printf("Unsigned/ZeroFill right shift '>>>' operator (Shifts the bits to the right and fills 0 on voids left as a result. The leftmost bit is set to 0  = (7>>>10)) :  %d %n",  (i>>>j));

		//instanceof operator
		Integer in = 5;
		System.out.println("instanceof operator (Integer i = 5 instanceof Integer) : " + (in instanceof Integer));
		newLines();
	}

	public static void typePromotion() {
		
		//types can be promoted  :  byte > short > int > long
								//  char > int > float > double
								//  long > double
								//  int > double
								//  long > float

		//No + operator for byte, both operands are promoted to int
		byte b1 = 7;
		byte b2 = 7;
		byte b3 = (byte) (b1 + b2); // byte = (byte) (int + int)
		System.out.println("byte b3 = b1 + b2; needs casting : byte b3 = (byte) (b1 + b2); + " + b3);
		
		//int + byte : compiler converts byte to int 
		byte b = 7;
		//b = 1 + b; doesn't compile b is converted to int byte = (int + int) casting needed
		b = (byte) (1 + b);
		System.out.println("byte b = 7; b = 1 + b; should be casted to : b = (byte) (1 + b); : " +  b);
		
		long l = 5;
		//int i = 6 + l; //cannot convert long to int
		int i = (int) (6 + l);
		System.out.println("long l = 5; int i = 6 + l; needs casting int i = (int) (6 + l); : " +  i);
	}

	private static void newLines() {
		System.out.println("");
		System.out.println("");
		System.out.print("-----------------------------------------------------");
		System.out.println("");
		System.out.println("");
	}
}