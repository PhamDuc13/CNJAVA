
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap 3 canh a, b, c: ");
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();

        if (a + b <= c || a + c <= b || b + c <= a) {
            System.out.println("Khong phai tam giac");
        } else if (a == b && b == c) {
            System.out.println("Tam giac deu");
        } else if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a) {
            System.out.println((a == b || b == c || a == c) ? "Tam giac vuong can" : "Tam giac vuong");
        } else if (a == b || b == c || a == c) {
            System.out.println("Tam giac can");
        } else {
            System.out.println("Tam giac thuong");
        }
    }
}
