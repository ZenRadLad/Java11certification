package objects;

interface MyIface {

	void simpleIfaceMethod();

	// Default = doesn't require implementation in implementing classes
	default void ifaceDefaultMethod() {
		privateIfaceMethod();
		System.out.println("Default myIface method");
	}

	// Static inteface method
	static void staticHelloMethod() {
		privateStaticIfaceMethod();
		System.out.println("Static myIface method");
	}

	private void privateIfaceMethod() {
		System.out.println("Private myIface method");
	}

	private static void privateStaticIfaceMethod() {
		System.out.println("Private static myIface method");
	}
}

@FunctionalInterface // permit only one abstract method
interface FunctionalIface {

	// Single Abstract Method interfaces (SAM)
	int functionalIfaceAdditionMethod(int a, int b);

	default void defaultFunctionalMethod() {
		System.out.println("FunctionalIface allow default methods since they're not abstract");
	}
}

public class Interfaces implements MyIface, FunctionalIface {

	public static void main(String[] args) {
		
		//TODO :
			//Advanced interface methods and implementations
			//indentify functionalIfaces, ustilize default, private, static methods
			

		Interfaces ifaces = new Interfaces();
		ifaces.ifaceDefaultMethod();
		ifaces.simpleIfaceMethod();
		ifaces.defaultFunctionalMethod();

		// Static interface method called with IfaceName.staticMethod()
		MyIface.staticHelloMethod();

		int sum = ifaces.functionalIfaceAdditionMethod(2, 3);
		System.out.println("functional Iface method implemented = " + sum);

	}

	@Override
	public void simpleIfaceMethod() {
		System.out.println("MyIface method implemented ");
	}

	@Override
	public int functionalIfaceAdditionMethod(int a, int b) {
		return a + b;
	}
}