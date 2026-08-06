
import java.util.Scanner;

public class tongdiem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ho va ten");
        String hoTen = sc.nextLine();
        System.out.println("Nhap ma SV: ");
        int maSV = sc.nextInt();
        
        System.out.println("Nhap diem cc: ");
                double dCC = sc.nextDouble();
        System.out.println("Nhap diem gk: ");
                double dGK = sc.nextDouble();
        System.out.println("Nhap diem ck: ");
                double dCK = sc.nextDouble();
                
        if( dCC <0 || dCC > 10 || dGK <0 || dGK >10 || dCK <0 || dCK >10)
            System.out.println("Diem so khong hop le");
            
        
        double tongDiem = dCC * 0.1 + dGK * 0.3 + dCK * 0.6;
        
        String xepLoai;
        if(tongDiem < 4.0 ){
            xepLoai =("F");
        }
        else if(tongDiem >= 8.5 ){
            xepLoai=("A");
        }
        else if(tongDiem >= 7.0 ){
            xepLoai=("B");
    }
        else if(tongDiem >= 5.5 ){
            xepLoai=("C");
    }
        else xepLoai=("D");
        
        System.out.printf("Ma SV: %s - Ho Ten: %s - Tong Diem: %.2f - Xep Loai: %s ",maSV, hoTen,tongDiem,xepLoai);
        
    }}
