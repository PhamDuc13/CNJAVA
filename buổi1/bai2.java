import java.util.Scanner;

public class TongNghichDao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập n: ");
        int n = scanner.nextInt();

        double s = 0.0;
        for (int i = 1; i <= n; i++) {
            s += 1.0 / i; // Dùng 1.0 để đảm bảo thực hiện phép chia số thực
        }

        System.out.printf("s = %.4f%n", s);
        
        scanner.close();
    }
}
