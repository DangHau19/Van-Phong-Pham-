package doan.menu;

import doan.list.DSPN;
import java.util.Scanner;

public class MenuPN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DSPN ds = new DSPN();
        int choice;
        String fileName = "phieunhap.txt";

        do {
            System.out.println("\n========= MENU PHIEU NHAP =========");
            System.out.println("1. Nhap danh sach");
            System.out.println("2. Xuat danh sach");
            System.out.println("3. Them phieu nhap");
            System.out.println("4. Xoa phieu nhap theo ma");
            System.out.println("5. Sua phieu nhap theo ma");
            System.out.println("6. Tim phieu nhap theo ma");
            System.out.println("7. Tim phieu nhap theo ngay");
            System.out.println("8. Thong ke so phieu nhap con no trong ngay");
            System.out.println("------------------------------------");
            System.out.println("9. Doc danh sach tu File");
            System.out.println("10. Ghi danh sach ra File");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1 -> ds.nhapDanhSach();
                case 2 -> ds.xuatDanhSach();
                case 3 -> ds.them();
                case 4 -> ds.xoa();
                case 5 -> ds.sua();
                case 6 -> ds.tim();
                case 7 -> {
                    System.out.print("Nhap ngay can tim (dd/mm/yyyy): ");
                    ds.timTheoNgay(sc.nextLine());
                }
                case 8 -> {
                    System.out.print("Nhap ngay can thong ke (dd/mm/yyyy): ");
                    ds.thongKePhieuNhapConNo(sc.nextLine());
                }
                case 9 -> ds.docFile(fileName);
                case 10 -> ds.ghiFile(fileName);
                case 0 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le. Vui long chon lai!");
            }
        } while (choice != 0);
    }
}