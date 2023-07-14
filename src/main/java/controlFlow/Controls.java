package controlFlow;

public class Controls {

	public static void main(String[] args) {
		// If else break continue, switch
		// Do/while, while, for, nested for
//		conditions();
//		loops();
		// TODO :
			// from a String get selected chars using loops and break/continue "PRRT" form
			// "PRRQRPPTT"
		
			//loop over 2D table Q&A
			char[][] arr = { { 'a', 'b' }, { 's', 'd' }, { 'f', 'g' } };
			for (char[] x : arr) {
				for (char y : x) {
					System.out.println(y);
				}
			}
	}

	private static void conditions() {
		String condition = "OK";
		String res = null;
		switch (condition) {
		case "OK":
		case "KO":
			res = "OK/KO";
			break;
		case "N/A":
			res = "N/A";
			break;
		default:
			res = "Default";
		}
		System.out.println(res);
	}

	private static void loops() {
		// while
		int i = 1;
		while (i < 11) {
			System.out.print(i + " ");
			i++;
		}
		i = newLine(i);

		// while syntax error
		// while();

		// do/while
		do {
			System.out.print(i + " ");
			i++;
		} while (i < 11);
		i = newLine(i);

		// do/while without braces
		do
			System.out.print(i + " ");
		while (++i < 11);
		i = newLine(i);

		// for
		for (int j = 1; j < 11; j++) {
			System.out.print(j + " ");
		}
		i = newLine(i);

		// continue
		for (int j = 1; j < 21; j++) {
			if (j % 2 == 1)
				continue;
			System.out.print(j + " ");
		}
		i = newLine(i);

		// break
		while (i < 12) {
			System.out.print(i + " ");
			i++;
			if (i == 11)
				break;
		}
		System.out.println();

		// infinite loops 
			// for(;;) {}
			// while(true)
			// do{}	while(true)

		// Enhanced for
		for (char c : "ABCDEFGHIK".toCharArray()) {
			System.out.print(c + " ");
		}
		System.out.println();

		// Nested loops
		for (int k = 1; k < 6; k++) {
			for (int j = 1; j < 5; j++) {
				System.out.print(j + " ");
				if (j == 4)
					System.out.println();
			}
		}

		// TODO loop with int i = 0, j = 0 in initialization and increm i++,j++Q
	}

	private static int newLine(int i) {
		System.out.println();
		i = 1;
		return i;
	}
}
