import java.util.Scanner;

public class TinhTongSoChan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập số nguyên n từ bàn phím
        System.out.print("Nhập số nguyên n: ");
        int n = scanner.nextInt();
        long S = 0;
        for (int i = 2; i <= n; i += 2) {
            S += i;
        }
        System.out.println("Tổng S = " + S);

        scanner.close();
    }
}
