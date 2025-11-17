package doan.menu;

import java.util.Scanner;
import doan.list.DSNCC;

public class MenuNCC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DSNCC ds = new DSNCC();
        int chon;
        String fileName = "nhacungcap.txt";

        do {
            System.out.println("\n===== MENU NHA CUNG CAP =====");
            System.out.println("1. Nhap danh sach");
            System.out.println("2. Xuat danh sach");
            System.out.println("3. Them nha cung cap");
            System.out.println("4. Xoa nha cung cap theo ma");
            System.out.println("5. Sua thong tin nha cung cap theo ma");
            System.out.println("6. Tim nha cung cap theo ma NCC");
            System.out.println("7. Tim nha cung cap theo ten san pham");
            System.out.println("8. Thong ke so luong cong ty theo dia chi");
            System.out.println("------------------------------");
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
                    System.out.print("Nhap san pham can tim: ");
                    ds.timTheoSanPham(sc.nextLine());
                }
                case 8 -> {
                    System.out.print("Nhap dia chi can thong ke so nha cung cap: ");
                    ds.thongKeSoLuongTheoDiaChi(sc.nextLine());
                }
                case 9 -> ds.docFile(fileName);
                case 10 -> ds.ghiFile(fileName);
                case 0 -> System.out.println("Dang thoat chuong trinh...");
                default -> System.out.println("Lua chon khong hop le, vui long chon lai!");
            }
        } while (chon != 0);
    }
}