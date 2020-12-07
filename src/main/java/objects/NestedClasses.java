package objects;

public class NestedClasses {

	public static void main(String[] args) {
		//nested classes used to logically group classes used ine one place
		//more readable and maintainable code (small classes)
		System.out.println("--------------------Inner Static Class--------------------");

		OuterSClass.InnerSClass.helloISC(null);
		
		OuterSClass.InnerSClass nestedInnerSClass = new OuterSClass.InnerSClass();
		nestedInnerSClass.helloISC("New Object from Inner Static Class");

		System.out.println("---------------------Inner Class-----------------");

		//To use Inner nonStatic Class, initiate OuterClass first and
		//use its object to initiate inner class's object
		OuterClass outerObject = new OuterClass();
		OuterClass.InnerClass innerObject = outerObject.new InnerClass();
		innerObject.helloNISC();

		System.out.printf("---------Shadowing (Inner class methods and fields shadow over outer methods and fields) -----------");

		//Shadowing
		ShadowOuterClass shadowOuterClass = new ShadowOuterClass();
		ShadowOuterClass.ShadowInnerClass nestedSC = shadowOuterClass.new ShadowInnerClass();
		nestedSC.innerShadowClassMethod(2020);
	}

	//Static nested
	static private class OuterSClass {


		static class InnerSClass {
			static private void helloISC(String message){
				System.out.println(message != null ? message : "Hello From Inner Static Class");
			}
		}

	}

	//Static nested
	static private class OuterClass {

		class InnerClass {
			private void helloNISC(){
				System.out.println("Hello From Inner Non Static Class");
			}
		}

	}
	
	//Shadowing
	static private class ShadowOuterClass {
		public int a = 0;

		class ShadowInnerClass {
			public int a = 10;

			void innerShadowClassMethod(int a){
				System.out.println("a = " + a); //method param a = 2020
				System.out.println("To Access Inner member use : this.attr = " + this.a);//firstLevelClass a = 10
				System.out.println("To Access Outer member use : OuterClass.this.attr = " + ShadowOuterClass.this.a); //OuterClass a = 0
			}
		}
	}
}