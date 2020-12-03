package dataTypes;

public class Strings {

	public static void main(String[] args) {
		stringManipulation();
	}


	public static void stringManipulation() {
		
		String s = "Lorem ipsum";
		String s2 = new String("Lorem ipsum");
		char[] charArray = {'L','o','r','e','m',' ','i','p','s','u','m'};
		String sCharArr = new String(charArray);
		
		//Equality
		System.out.println("------------Equality-------------");
		System.out.println("	using == (reference check) :   " + (s == s2) + " because the two strings aren't in the same string pool");
		System.out.println("	using .equals() (value check) : " + s.equals(s2));
		System.out.println("	String(charArray) using .equals() : " + sCharArr.equals(s2));
		Object strObj = "test";
		System.out.println("	Object s = 'test' : " + (strObj));
		System.out.println("	String s += s2 (appending) : " + (s+= s2));
		
		//String pool : 
			//Any time you create a new string without using the new keyword, Java checks whether the
			//same string already exists in the string pool. If it does, Java returns a reference to
			//the same String object and if it does not, Java creates a new String object in the
			//string pool and returns its reference.
		System.out.println("------------String pool-------------");
		String str = "hello";
		String str2 = "hello";
		String str3 = new String("hello");
		String str4 = new String("hello");
		System.out.println("	str == str2 (same string pool) : " + (str == str2));
		System.out.println("	str == str2 (not same string pool) : " + (str == str3));
		System.out.println("	str == str2 (string pool) : " + (str4 == str3));

		//Immutability
		System.out.println("------------Immutability-------------");
		String st = "lo";
		st.concat("rem");
		st.toUpperCase();
		System.out.println("	'lo'.concat('rem') .toUpperCase yeilds no change : " + (st));

		//Methods
		String sr = "LoremIpsum";
		System.out.println("------------Methods-------------");
		System.out.println("	'LoremIpsum'.length() : " + (sr.length()));
		System.out.println("	'LoremIpsum'.charAt(0) : " + (sr.charAt(0)));
		System.out.println("	'LoremIpsum'.indexOf('z') (returns -1 if not found)  : " + (sr.indexOf('z')));
		System.out.println("	'LoremIpsum'.substring(startIdx Inclusive, endIdx Exclusive) : " + (sr.substring(5,10)));
		System.out.println("	'LoremIpsum'.substring(startIdx) : " + (sr.substring(5)));
		System.out.println("	'lorem'.concat('ipsum') : " + ("lorem".concat("ipsum")));
		System.out.println("	'LoremIpsum'.toLowerCase() : " + (sr.toLowerCase()));
		System.out.println("	'LoremIpsum'.toUpperCase() : " + (sr.toUpperCase()));
		System.out.println("	str.strip() (leading and trailling space) : " + ("  strip  ".strip()));
		System.out.println("	str.stripLeading() : " + (" leadingSpace".stripLeading()));
		System.out.println("	str.stripTrailing() : " + ("trailingSpace   ".stripTrailing()));
		System.out.println("	'LoremIpsum'.isBlank() : " + (sr.isBlank()));
		System.out.println("	'LoremIpsum'.isEmpty() : " + (sr.isEmpty()));
		System.out.println("	'LoremIpsum'.equalsIgnoreCase('LoREMIpsum') : " + (sr.equalsIgnoreCase("LoREMIpsum")));
		System.out.println("	'LoremIpsum2'.compareTo('LoremIpsumA') (lexicographical/ alphabetical order comparaison) : " + ("LoremIpsumZ".compareTo("LoremIpsumA")));
	}
}