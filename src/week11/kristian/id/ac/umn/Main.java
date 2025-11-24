package week11.kristian.id.ac.umn;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<User> listOfUser = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialize();

        while (true) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan : ");

            String pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                try {
                    handleLogin();
                } catch (ExcessiveFailedLoginException e) {
                    System.out.println(e.getMessage() != null ? e.getMessage() : "Anda telah mencapai jumlah batas login");
                } catch (AuthenticationException e) {
                    System.out.println(e.getMessage());
                }
            } else if (pilihan.equals("2")) {
                handleSignUp();
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void initialize() {
        User admin = new User("John", "Doe", 'L', 
                              "Jl. Merpati No. 1 RT 1 RW 1, Banten", 
                              "admin", "admin");
        listOfUser.add(admin);
    }

    public static void handleLogin() throws ExcessiveFailedLoginException, AuthenticationException {
        System.out.println("\nMenu Login");
        
        System.out.print("Username : ");
        String username = scanner.nextLine();
        
        System.out.print("Password : ");
        String password = scanner.nextLine();

        boolean isLoginSuccessful = false;

        for (User user : listOfUser) {
            if (user.login(username, password)) {
                System.out.println(user.greeting());
                isLoginSuccessful = true;
                break; 
            	}
        }

        if (!isLoginSuccessful) {
           throw new AuthenticationException("Username / password salah");
        }
    }

    public static void handleSignUp() {
        System.out.println("\nMenu Lihat Sign Up");

        System.out.print("Nama Depan : ");
        String firstName = scanner.nextLine();

        System.out.print("Nama Belakang : ");
        String lastName = scanner.nextLine();

        System.out.print("Jenis Kelamin (L/P) : ");
        char gender = scanner.nextLine().charAt(0);

        System.out.print("Alamat : ");
        String address = scanner.nextLine();

        System.out.print("Username : ");
        String username = scanner.nextLine();

        if (username.length() <= 8) {
            System.out.println("Username harus lebih dari 8 karakter");
            return;
        }

        System.out.print("Password : ");
        String password = scanner.nextLine();

        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z]).{6,16}$";
        if (!password.matches(passwordRegex)) {
            System.out.println("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter");
            return;
        }

        User newUser = new User(firstName, lastName, gender, address, username, password);
        listOfUser.add(newUser);
        
        System.out.println("User telah berhasil didaftarkan");
    }
}
