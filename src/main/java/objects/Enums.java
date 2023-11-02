package objects;

import java.util.Arrays;

public class Enums {

    public static void main(String[] args) {
        UserRole user = UserRole.USER;
        UserRole admin = UserRole.ADMIN;

        System.out.println("valueOf(root) : " + UserRole.valueOf("ROOT"));
        System.out.println("Compare enum instances : " + (user == admin));
        System.out.println("Method call : " + user.getRoleInUpperCase());
        System.out.println("ordinal() returns order of an enum member starting from 0 : " + admin.ordinal());

        UserRole[] rolesList = UserRole.values();
        System.out.println("enum.values() : " + Arrays.toString(rolesList));
        System.out.println("-----------------------------");
        System.out.println(Operation.ADD.apply(13, 53));
        String val = null;
      
    }

    public enum UserRole {
        USER("user"), MODERATOR("moderator"), ADMIN("admin"), ROOT("root");

        //Enums can have :
        // static initializer
        static {
            System.out.println("static initializers of UserRole");
        }

        // fields, methods, and constructors. However, constructors in an enum are always private.
        private final String role;

        // constructors are always private
        UserRole(String role) {
            this.role = role;
        }

        // methods
        public String getRoleInUpperCase() {
            return this.role.toUpperCase();
        }

        public String toString() {
            return "Role : " + this.role;
        }
    }

    // abstract methods with each member defining an implementation
    enum Operation {
        ADD {
            @Override
            public int apply(int a, int b) {
                return a + b;
            }
        },
        SUBTRACT {
            @Override
            public int apply(int a, int b) {
                return a - b;
            }
        };

        public abstract int apply(int a, int b);
    }

}