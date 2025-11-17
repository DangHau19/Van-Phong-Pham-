
package doan.list;

import doan.single.CTPN;
import doan.chucnang.IChucNang;
import java.io.*;
import java.util.*;

public class DSCTPN implements IChucNang {
    private CTPN[] ds = new CTPN[0];
    private final Scanner sc = new Scanner(System.in);

    private static final int MAX_FILE_SIZE = 1000;

    // Phương thức docFile
    @Override
    public void docFile(String file) {
        CTPN[] dsFile = new CTPN[MAX_FILE_SIZE];
        int count = 0; // Số lượng phần tử thực tế đọc được
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null && count < MAX_FILE_SIZE) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    try {
                        CTPN ctpn = new CTPN(
                                parts[0], // maCTPN
                                parts[1], // maPhieu
                                parts[2], // tenHang
                                Integer.parseInt(parts[3]), // soLuong
                                Double.parseDouble(parts[4]) // donGia
                        );
                        dsFile[count] = ctpn;
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Canh bao: Bo qua dong du lieu sai dinh dang so: " + line);
                    }
                }
            }
            // Cắt mảng tạm về kích thước thực tế
            ds = Arrays.copyOf(dsFile, count);

            System.out.println("\nDoc file thanh cong tu: " + file);
            if (count == MAX_FILE_SIZE) {
                System.out.println("Canh bao: Chi doc duoc toi da " + MAX_FILE_SIZE + " chi tiet phieu nhap.");
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
            for (CTPN ctpn : ds) {
                String line = ctpn.getMaCTPN() + ";" +
                        ctpn.getMaPhieu() + ";" +
                        ctpn.getTenHang() + ";" +
                        ctpn.getSoLuong() + ";" +
                        ctpn.getDonGia();
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
        System.out.print("Nhap so luong chi tiet phieu nhap: ");
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
        ds = new CTPN[n];
        for (int i = 0; i < n; i++) {
            System.out.println("--- Nhap chi tiet phieu nhap thu " + (i + 1) + " ---");
            ds[i] = new CTPN();
            ds[i].nhap(sc);
        }
    }

    // Phương thức xuatDanhSach
    @Override
    public void xuatDanhSach() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach chi tiet phieu nhap rong.");
            return;
        }
        System.out.println("\n========= DANH SACH CHI TIET PHIEU NHAP =========");
        System.out.printf("%-10s %-10s %-15s %-8s %-10s %-10s\n",
                "MaCTPN", "MaPN", "TenHang", "SL", "DonGia", "ThanhTien");
        System.out.println("----------------------------------------------------------------------");
        for (CTPN c : ds)
            c.xuat();
        System.out.println("====================================================================");
    }

    // Phương thức kiểm tra mã trùng
    public boolean kiemTraTrungMa(String ma) {
        for (CTPN ctpn : ds) {
            // Giả sử bạn kiểm tra trùng mã Chi Tiết Phiếu Nhập
            if (ctpn.getMaCTPN().equalsIgnoreCase(ma)) {
                return true; // Bị trùng mã
            }
        }
        return false; // Không trùng mã
    }

    // Phương thức them
    @Override
    public void them() {
        CTPN ctpn = new CTPN();
        System.out.println("--- Nhap thong tin chi tiet phieu nhap can them ---");
        ctpn.nhap(sc);
        String maMoi = ctpn.getMaCTPN();
        if (kiemTraTrungMa(maMoi)) {
            System.out.println("Loi: Ma chi tietphieu nhap '" + maMoi + "' da ton tai! Khong the them.");
            return;
        }
        themCTPN(ctpn);
        System.out.println("\nDa them chi tiet phieu nhap moi thanh cong.");
    }

    public void themCTPN(CTPN ctpn) {
        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[ds.length - 1] = ctpn;
    }

    // Phương thức xoa
    @Override
    public void xoa() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the xoa.");
            return;
        }
        System.out.print("Nhap ma chi tiet phieu nhap can xoa: ");
        String ma = sc.nextLine();
        xoaTheoMaCTPN(ma);
    }

    public void xoaTheoMaCTPN(String ma) {
        int index = -1;
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getMaPhieu().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("\nKhong tim thay ma chi tiet phieu nhap!");
            return;
        }
        for (int i = index; i < ds.length - 1; i++) {
            ds[i] = ds[i + 1];
        }
        ds = Arrays.copyOf(ds, ds.length - 1);
        System.out.println("\nDa xoa chi tiet phieu nhap co ma " + ma);
    }

    // Phương thức sua
    @Override
    public void sua() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the sua.");
            return;
        }
        System.out.print("Nhap ma chi tiet phieu nhap can sua: ");
        String maCTPN = sc.nextLine();
        suaThongTinCTPN(maCTPN);
    }

    public void suaThongTinCTPN(String maCTPN) {
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getMaCTPN().equalsIgnoreCase(maCTPN)) {
                System.out.println("\n--- Chon thong tin can sua cho CTPN " + maCTPN + " ---");
                System.out.println("1. Sua ma phieu nhap\n2. Sua ten hang\n3. Sua so luong\n4. Sua don gia");
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
                        System.out.print("Nhap ma phieu nhap moi: ");
                        ds[i].setMaPhieu(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nhap ten hang moi: ");
                        ds[i].setTenHang(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Nhap so luong moi: ");
                        try {
                            ds[i].setSoLuong(Integer.parseInt(sc.nextLine()));
                        } catch (NumberFormatException e) {
                            System.out.println("So luong khong hop le!");
                        }
                    }
                    case 4 -> {
                        System.out.print("Nhap don gia moi: ");
                        try {
                            ds[i].setDonGia(Double.parseDouble(sc.nextLine()));
                        } catch (NumberFormatException e) {
                            System.out.println("Don gia khong hop le!");
                        }
                    }
                    default -> System.out.println("Lua chon khong hop le!");
                }
                System.out.println("\nDa sua thong tin chi tiet phieu nhap " + maCTPN);
                return;
            }
        }
        System.out.println("\nKhong tim thay ma chi tiet phieu nhap!");
    }

    // Phương thức tim
    @Override
    public void tim() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the tim kiem.");
            return;
        }
        System.out.print("Nhap ma chi tiet phieu nhap (Ma CTPN) can tim: ");
        String ma = sc.nextLine();
        timTheoMaCTPN(ma);
    }

    public void timTheoMaCTPN(String ma) {
        System.out.println("\n--- Ket qua tim kiem theo Ma Chi Tiet Phieu Nhap ---");
        boolean found = false;
        System.out.printf("%-10s %-10s %-15s %-8s %-10s %-10s\n",
                "MaCTPN", "MaPhieu", "TenHang", "SoLuong", "DonGia", "ThanhTien");
        System.out.println("----------------------------------------------------------------------");
        for (CTPN c : ds) {
            if (c.getMaCTPN().equalsIgnoreCase(ma)) {
                c.xuat();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nKhong tim thay chi tiet phieu nhap co ma " + ma);
        }
    }

    // Tìm theo mã phiếu nhập
    public void timTheoMaPhieu(String ma) {
        System.out.println("\n--- Ket qua tim kiem theo Ma Phieu Nhap ---");
        boolean found = false;
        System.out.printf("%-10s %-10s %-15s %-8s %-10s %-10s\n",
                "MaCTPN", "MaPhieu", "TenHang", "SoLuong", "DonGia", "ThanhTien");
        System.out.println("----------------------------------------------------------------------");
        for (CTPN c : ds) {
            if (c.getMaPhieu().equalsIgnoreCase(ma)) {
                c.xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nKhong tim thay chi tiet phieu nhap nao cho ma phieu " + ma);
        }
    }

    // Thống kê tổng tiền theo mã phiếu nhập
    public void thongKeTongTienTheoPhieu(String maPhieu) {
        double tong = 0;
        for (CTPN c : ds)
            if (c.getMaPhieu().equalsIgnoreCase(maPhieu))
                tong += c.getThanhTien();
        System.out.printf("\nTong tien cua phieu nhap %s: %.2f\n", maPhieu, tong);
    }
}