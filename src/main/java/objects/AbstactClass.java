package objects;

public class AbstactClass {

	// Cannot be instanciated, abstract and concrete methods

	static abstract class Sport {
		abstract public void play();
		public void getSportName(String name){
			System.out.println("getSportName : " + name);
		}
	}

	static class Tennis extends Sport {
		public void play() {
			System.out.println("play sport tennis");
			getSportName("tennis");
		}
	}

	static class Football extends Sport {
		public void play() {
			System.out.println("play sport football");
			getSportName("football");

		}
	}

	public static void main(String[] args) {
		Tennis tennis = new Tennis();
		tennis.play();

		Football football = new Football();
		football.play();
	}
}