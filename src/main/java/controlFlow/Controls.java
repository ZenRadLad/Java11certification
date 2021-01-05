package controlFlow;

public class Controls {

	public static void main(String[] args) {
		// If else break continue, switch
		// Do/while, while, for, nested for
		conditions();
		loops();
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

		// infinite for => for(;;) {}

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
	}

	private static int newLine(int i) {
		System.out.println();
		i = 1;
		return i;
	}
}
