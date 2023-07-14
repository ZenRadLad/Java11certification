package objects;

import java.util.Arrays;

public class Enums {

	public static void main(String[] args) {
		UserRole user = UserRole.USER;
		UserRole admin = UserRole.ADMIN;

		System.out.println("Compare enum instances : " + (user == admin));
		System.out.println("Override toString() : " + user.toString());
		System.out.println("Order of admin role : " + admin.ordinal());

		UserRole[] rolesList = UserRole.values();
		System.out.println("enum.values() : " + Arrays.toString(rolesList));
		
		//TODO : 
			//Advanced enumerations : creation and usage
			//Q&As 
	}

	public enum UserRole {
		USER("user"), MODERATOR("moderator"), ADMIN("admin"), ROOT("root");

		private String role;

		UserRole(String role) {
			this.role = role;
		}

		public String getUserRole() {
			return this.role;
		}

		public String toString() {
			return "Role : " + this.role;
		}
	}
}