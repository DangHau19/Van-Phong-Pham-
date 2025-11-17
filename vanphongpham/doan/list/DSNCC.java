package doan.list;

import java.io.*;
import java.util.*;
import doan.single.NCC;
import doan.chucnang.IChucNang;

public class DSNCC implements IChucNang {
    private NCC[] ds = new NCC[0];
    private final Scanner sc = new Scanner(System.in);

    private static final int MAX_FILE_SIZE = 1000;

    // Phương thức docFile
    @Override
    public void docFile(String file) {
        NCC[] dsFile = new NCC[MAX_FILE_SIZE];
        int count = 0; // Số lượng phần tử thực tế đọc được
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null && count < MAX_FILE_SIZE) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    NCC ncc = new NCC(
                            parts[0], // maNCC
                            parts[1], // tenNCC
                            parts[2], // diaChi
                            parts[3], // soDienThoai
                            parts[4] // tenSanPham
                    );
                    dsFile[count] = ncc;
                    count++;
                }
            }
            // Cắt mảng tạm về kích thước thực tế
            ds = Arrays.copyOf(dsFile, count);
            System.out.println("\nDoc file thanh cong tu: " + file);
            if (count == MAX_FILE_SIZE) {
                System.out.println("Canh bao: Chi doc duoc toi da " + MAX_FILE_SIZE + " nha cung cap.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nLoi: Khong tim thay file " + file);
        } catch (IOException e) {
            System.out.println("\nLoi doc/ghi file: " + e.getMessage());
        }
    }

    // Phương thức ghiFile
    @Override
    public void ghiFile(String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (NCC ncc : ds) {
                String line = ncc.getMaNCC() + ";" +
                        ncc.getTenNCC() + ";" +
                        ncc.getDiaChi() + ";" +
                        ncc.getSoDienThoai() + ";" +
                        ncc.getTenSanPham();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("\nGhi file thanh cong vao: " + file);
        } catch (IOException e) {
            System.out.println("\nLoi khi ghi file: " + e.getMessage());
        }
    }

    // Phương thức nhapDanhSach
    @Override
    public void nhapDanhSach() {
        System.out.print("Nhap so luong nha cung cap: ");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Loi nhap so luong. Mac dinh la 0.");
            n = 0;
        }
        if (ds.length > 0) {
            System.out.print("Danh sach da co du lieu. Ban co muon ghi de (Y/N)? ");
            String confirm = sc.nextLine();
            if (!confirm.equalsIgnoreCase("Y")) {
                System.out.println("Huy nhap danh sach moi.");
                return;
            }
        }
        ds = new NCC[n];
        for (int i = 0; i < n; i++) {
            System.out.println("--- Nhap nha cung cap thu " + (i + 1) + " ---");
            ds[i] = new NCC();
            ds[i].nhap(sc);
        }
    }

    // Phương thức xuatDanhSach
    @Override
    public void xuatDanhSach() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach nha cung cap rong.");
            return;
        }
        System.out.println("\n============= DANH SACH NHA CUNG CAP =============");
        System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
                "MaNCC", "TenNCC", "DiaChi", "SDT", "TenSP");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (NCC ncc : ds)
            ncc.xuat();
        System.out.println("==============================================================================================");
    }

    // Phương thức kiểm tra mã trùng
    public boolean kiemTraTrungMa(String ma) {
        for (NCC ncc : ds) {
            if (ncc.getMaNCC().equalsIgnoreCase(ma)) {
                return true; // Bị trùng mã
            }
        }
        return false; // Không trùng mã
    }

    // Phương thức them
    @Override
    public void them() {
        NCC ncc = new NCC();
        System.out.println("--- Nhap thong tin nha cung cap can them ---");
        ncc.nhap(sc);
        String maMoi = ncc.getMaNCC();
        if (kiemTraTrungMa(maMoi)) {
            System.out.println("Loi: Ma nha cung cap '" + maMoi + "' da ton tai! Khong the them.");
            return;
        }
        themNCC(ncc);
        System.out.println("\nDa them nha cung cap moi thanh cong.");
    }

    public void themNCC(NCC ncc) {
        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[ds.length - 1] = ncc;
    }

    // Phương thức xoa
    @Override
    public void xoa() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the xoa.");
            return;
        }
        System.out.print("Nhap ma nha cung cap can xoa: ");
        String ma = sc.nextLine();
        xoaTheoMaNCC(ma);
    }

    public void xoaTheoMaNCC(String ma) {
        int index = -1;
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("\nKhong tim thay ma nha cung cap!");
            return;
        }
        for (int i = index; i < ds.length - 1; i++) {
            ds[i] = ds[i + 1];
        }
        ds = Arrays.copyOf(ds, ds.length - 1);
        System.out.println("\nDa xoa nha cung cap co ma " + ma);
    }

    // Phương thức sua
    @Override
    public void sua() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the sua.");
            return;
        }
        System.out.print("Nhap ma nha cung cap can sua: ");
        String maNCC = sc.nextLine();
        suaThongTinNCC(maNCC);
    }

    public void suaThongTinNCC(String maNCC) {
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getMaNCC().equalsIgnoreCase(maNCC)) {
                System.out.println("\n--- Chon thong tin can sua cho NCC " + maNCC + " ---");
                System.out
                        .println("1. Sua ten nha cung cap\n2. Sua dia chi\n3. Sua so dien thoai\n4. Sua ten san pham");
                System.out.print("Sua thong tin so: ");
                int chon;
                try {
                    chon = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Lua chon khong hop le!");
                    return;
                }

                switch (chon) {
                    case 1 -> {
                        System.out.print("Nhap ten nha cung cap moi: ");
                        ds[i].setTenNCC(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nhap dia chi moi: ");
                        ds[i].setDiaChi(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Nhap so dien thoai moi: ");
                        ds[i].setSoDienThoai(sc.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Nhap ten san pham moi: ");
                        ds[i].setTenSanPham(sc.nextLine());
                    }
                    default -> System.out.println("Lua chon khong hop le!");
                }
                System.out.println("\nDa sua thong tin nha cung cap " + maNCC);
                return;
            }
        }
        System.out.println("\nKhong tim thay ma nha cung cap!");
    }

    // Phương thức tim
    @Override
    public void tim() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the tim kiem.");
            return;
        }
        System.out.print("Nhap ma nha cung cap can tim: ");
        String ma = sc.nextLine();
        timTheoMaNCC(ma);
    }

    public void timTheoMaNCC(String ma) {
        System.out.println("\n--- Ket qua tim kiem theo Ma NCC ---");
        boolean found = false;
        System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
                "MaNCC", "TenNCC", "DiaChi", "SDT", "TenSP");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (NCC ncc : ds) {
            if (ncc.getMaNCC().equalsIgnoreCase(ma)) {
                ncc.xuat();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nKhong tim thay nha cung cap co ma " + ma);
        }
    }

    // Tìm theo sản phẩm
    public void timTheoSanPham(String sp) {
        System.out.println("\n--- Cac nha cung cap co san pham \"" + sp + "\" ---");
        boolean found = false;
        System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
                "MaNCC", "TenNCC", "DiaChi", "SDT", "TenSP");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (NCC ncc : ds)
            if (ncc.getTenSanPham().equalsIgnoreCase(sp)) {
                ncc.xuat();
                found = true;
            }
        if (!found)
            System.out.println("\nKhong tim thay san pham nao trung khop!");
    }

    // Thống kê số lượng công ty ở địa chỉ ...
    public void thongKeSoLuongTheoDiaChi(String dc) {
        int dem = 0;
        for (NCC ncc : ds)
            if (ncc.getDiaChi().equalsIgnoreCase(dc))
                dem++;
        System.out.println("\nSo luong cong ty o dia chi \"" + dc + "\": " + dem);
    }
}