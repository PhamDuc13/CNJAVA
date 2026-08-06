import java.util.Scanner;

public class SoNguyenTo {
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập n: ");
        int n = scanner.nextInt();

        if (isPrime(n)) {
            System.out.println(n + " là số nguyên tố");
        } else {
            System.out.println(n + " không phải là số nguyên tố");
        }

        scanner.close();
    }
}
