package objects;

public class Inheritance {

	static class Vehicle {
		protected String model;
				
		public void getVehicleMaxSpeed(int speed) {
		    System.out.println(speed);
		}
		
		public void setCarModel(String model) {
			this.model = model;
		}
	}
	
	static class Car extends Vehicle {
		public void getCarInfo() {
			Car car = new Car();
			car.setCarModel("Opel");
			car.getVehicleMaxSpeed(240);
		    System.out.println(car.model);
		}		
	}
	
	public static void main(String[] args) {
		Car car = new Car();
		car.getCarInfo();
	}
}