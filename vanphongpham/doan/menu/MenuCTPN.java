package doan.menu;

import java.util.*;
import doan.list.DSCTPN;

public class MenuCTPN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DSCTPN ds = new DSCTPN();
        int chon;
        String fileName = "chitietpn.txt";

        do {
            System.out.println("\n===== MENU CHI TIET PHIEU NHAP =====");
            System.out.println("1. Nhap danh sach");
            System.out.println("2. Xuat danh sach");
            System.out.println("3. Them chi tiet phieu nhap");
            System.out.println("4. Xoa chi tiet phieu nhap theo ma");
            System.out.println("5. Sua thong tin chi tiet phieu nhap theo ma");
            System.out.println("6. Tim chi tiet phieu nhap theo ma CTPN");
            System.out.println("7. Tim chi tiet phieu nhap theo ma phieu");
            System.out.println("8. Thong ke tong tien theo ma phieu");
            System.out.println("------------------------------------");
            System.out.println("9. Doc danh sach tu File");
            System.out.println("10. Ghi danh sach ra File");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                chon = -1;
            }
            switch (chon) {
                case 1 -> ds.nhapDanhSach();
                case 2 -> ds.xuatDanhSach();
                case 3 -> ds.them();
                case 4 -> ds.xoa();
                case 5 -> ds.sua();
                case 6 -> ds.tim();
                case 7 -> {
                    System.out.print("Nhap ma phieu nhap (maPN) can tim: ");
                    ds.timTheoMaPhieu(sc.nextLine());
                }
                case 8 -> {
                    System.out.print("Nhap ma phieu nhap (maPN) can thong ke tong tien: ");
                    ds.thongKeTongTienTheoPhieu(sc.nextLine());
                }
                case 9 -> ds.docFile(fileName);
                case 10 -> ds.ghiFile(fileName);
                case 0 -> System.out.println("Dang thoat chuong trinh...");
                default -> System.out.println("Lua chon khong hop le, vui long chon lai!");
            }
        } while (chon != 0);
    }
}