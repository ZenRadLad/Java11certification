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
	}

	public enum UserRole {
		USER("user"), MODERATOR("moderator"), ADMIN("admin"), ROOT("root");

		private String userRole;

		UserRole(String role) {
			this.userRole = role;
		}

		public String getUserRole() {
			return this.userRole;
		}

		public String toString() {
			return "Role : " + this.userRole;
		}
	}
}