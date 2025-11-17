package doan.menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import doan.menu.MenuPN;
import doan.menu.MenuCTPN;
import doan.menu.MenuNCC;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n===== MENU CHINH =====");
            System.out.println("1. Quan ly Phieu Nhap");
            System.out.println("2. Quan ly Chi Tiet Phieu Nhap");
            System.out.println("3. Quan ly Nha Cung Cap");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        MenuPN.main(null);
                    }
                    case 2 -> {
                        MenuCTPN.main(null);
                    }
                    case 3 -> {
                        MenuNCC.main(null);
                    }
                    case 0 -> System.out.println("Thoat chuong trinh.");
                    default -> System.out.println("Loi: Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Loi nhap lieu: Vui long nhap mot so tu 0 den 3.");
                choice = -1;
            }
        } while (choice != 0);

        sc.close();
    }
}