package objects;

import java.util.ArrayList;
import java.util.List;

public class ClassFields {

	static class Car {
		
		private String carManufacturer;
		private String carModel;
		private String carColor;
		private boolean isCarElectric;
		
		private static int carProductionYear; //static fields belongs to class not its instance
		
		{//static initializer, executed before constructor
			carProductionYear = 2020;
		}
		
		public Car(String carManuf, String carModel) {
			this.carManufacturer = carManuf;
			this.carModel = carModel;
		}
				
		public static List<Car> getCarsList(Car... cars) {
			List<Car> carsList = new ArrayList<>();
			for(Car c : cars){
				carsList.add(c);
			}
			return carsList;
		}
		
		public void setCarProperty(String color) {
			this.carColor = color;
		}
		
		public void setCarProperty(boolean isElectric) {
			this.isCarElectric = isElectric;
		}

		public static void toString(List<Car> cars){
			for(Car c : cars){
				System.out.println(c.carColor + " " + c.carManufacturer + " " 
						+ c.carModel + " " + c.carProductionYear + " - " + (c.isCarElectric ? "ICE" : "Electrical") + " engine");
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		//TODO : 
			//Variable scopes, apply encapsulation, make objects immutable
			//subsclasses, superclasses, abstract classes
			//nested class objects, dereferencing by assignment, when object/string is GCed
			//super() this() advanced when are they called, implicit vs explicit 
			//Static access static methods nested classes
			//Polymorphism and Casting to call methods
			//Object type vs reference type
		
		ClassFields.Car ferrariCar = new ClassFields.Car("Ferrari", "LaFerrari");
		ClassFields.Car lamboCar = new ClassFields.Car("Lamborghini", "Gaillardo");
		ClassFields.Car teslaCar = new ClassFields.Car("Tesla", "Model X");
	
		//method overloading : Same method signature = change paramType, paramNumbers
		ferrariCar.setCarProperty("Red");
		ferrariCar.setCarProperty(false);
		
		lamboCar.setCarProperty("Green");
		lamboCar.setCarProperty(false);
		
		teslaCar.setCarProperty("Silver");
		teslaCar.setCarProperty(true);
		
		List<ClassFields.Car> carsList = ClassFields.Car.getCarsList(ferrariCar, lamboCar, teslaCar);
		ClassFields.Car.toString(carsList);	
	}	
}