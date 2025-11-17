package doan.list;

import doan.single.PN;
import doan.chucnang.IChucNang;
import java.io.*;
import java.util.*;

public class DSPN implements IChucNang {
    private PN[] ds = new PN[0];
    private final Scanner sc = new Scanner(System.in);

    private static final int MAX_FILE_SIZE = 1000;

    // Phương thức docFile
    @Override
public void docFile(String file) {
    PN[] dsFile = new PN[MAX_FILE_SIZE];
    int count = 0; 

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null && count < MAX_FILE_SIZE) {
            String[] parts = line.split(";");
            if (parts.length == 5) { 
                try {
                    PN p = new PN(
                            parts[0], // maPhieu
                            parts[1], // maNhaCungCap
                            parts[2], // ngayNhap
                            parts[3], // loaiSanPhamChung  
                            parts[4] // ghiChu 
                    ); 
                    dsFile[count] = p;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println("Canh bao: Bo qua dong du lieu sai dinh dang so: " + line);
                }
            }
        }
        ds = Arrays.copyOf(dsFile, count);
        System.out.println("\nDoc file thanh cong tu: " + file);
        if (count == MAX_FILE_SIZE) {
            System.out.println("Canh bao: Chi doc duoc toi da " + MAX_FILE_SIZE + " phieu nhap.");
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
        for (PN p : ds) { 
            String line = p.getMaPhieu() + ";" +
                    p.getMaNhaCungCap() + ";" +
                    p.getNgayNhap() + ";" +
                    p.getLoaiSanPhamChung() + ";" + 
                    p.getGhiChu(); 
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
        System.out.print("Nhap so luong phieu nhap: ");
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
        ds = new PN[n];
        for (int i = 0; i < n; i++) {
            System.out.println("--- Nhap phieu nhap thu " + (i + 1) + " ---");
            ds[i] = new PN();
            ds[i].nhap(sc);
        }
    }

    // Phương thức xuatDanhSach
   @Override
public void xuatDanhSach() {
    if (ds.length == 0) {
        System.out.println("\nDanh sach phieu nhap rong.");
        return;
    }
    System.out.println("\n================ DANH SACH PHIEU NHAP ================");
    System.out.printf("%-10s %-10s %-12s %-25s %-15s\n",
            "MaPN", "MaNCC", "NgayNhap", "LoaiSPChung", "GhiChu");
    System.out.println("--------------------------------------------------------------------");
    for (PN p : ds) {
        p.xuat();
    }
    System.out.println("====================================================================");
}

    // Phương thức kiểm tra mã trùng
    public boolean kiemTraTrungMa(String ma) {
        for (PN pn : ds) {
            if (pn.getMaPhieu().equalsIgnoreCase(ma)) {
                return true; // Bị trùng mã
            }
        }
        return false; // Không trùng mã
    }

    // Phương thức them
    @Override
    public void them() {
        PN p = new PN();
        System.out.println("--- Nhap thong tin phieu nhap can them ---");
        p.nhap(sc);
        String maMoi = p.getMaPhieu();
        if (kiemTraTrungMa(maMoi)) {
            System.out.println("Loi: Ma phieu nhap '" + maMoi + "' da ton tai! Khong the them.");
            return;
        }
        themPN(p);
        System.out.println("\nDa them phieu nhap moi thanh cong.");
    }

    public void themPN(PN p) {
        ds = Arrays.copyOf(ds, ds.length + 1);
        ds[ds.length - 1] = p;
    }

    // Phương thức xoa
    @Override
    public void xoa() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the xoa.");
            return;
        }
        System.out.print("Nhap ma phieu nhap can xoa: ");
        String ma = sc.nextLine();
        xoaTheoMaPN(ma);
    }

    public void xoaTheoMaPN(String ma) {
        int index = -1;
        for (int i = 0; i < ds.length; i++) {
            if (ds[i].getMaPhieu().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("\nKhong tim thay ma phieu!");
            return;
        }
        for (int i = index; i < ds.length - 1; i++) {
            ds[i] = ds[i + 1];
        }
        ds = Arrays.copyOf(ds, ds.length - 1);
        System.out.println("\nDa xoa phieu nhap co ma " + ma);
    }

    // Phương thức sua
    @Override
    public void sua() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the sua.");
            return;
        }
        System.out.print("Nhap ma phieu nhap can sua: ");
        String maPhieu = sc.nextLine();
        suaPhieuNhap(maPhieu);
    }

    public void suaPhieuNhap(String maPhieu) {
    for (int i = 0; i < ds.length; i++) {
        if (ds[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
            System.out.println("\n--- Chon thong tin can sua cho phieu " + maPhieu + " ---");
            System.out.println(
                    "1. Sua ma nha cung cap\n2. Sua ngay nhap\n3. Sua loai san pham chung\n4. Sua Ghi chu");
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
                    System.out.print("Nhap ma NCC moi: ");
                    ds[i].setMaNhaCungCap(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Nhap ngay nhap moi: ");
                    ds[i].setNgayNhap(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Nhap loai san pham chung moi: ");
                    ds[i].setLoaiSanPhamChung(sc.nextLine());
                }   
                case 4 -> {
                    System.out.print("Nhap Ghi chu moi (Da tra/Con no): ");
                    ds[i].setGhiChu(sc.nextLine());
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
            System.out.println("\nDa sua thong tin phieu nhap " + maPhieu);
            return;
        }
    }
    System.out.println("\nKhong tim thay phieu nhap can sua!");
}

    // Phương thức tim
    @Override
    public void tim() {
        if (ds.length == 0) {
            System.out.println("\nDanh sach rong. Khong the tim kiem.");
            return;
        }
        System.out.print("Nhap ma phieu can tim: ");
        String ma = sc.nextLine();
        timTheoMa(ma);
    }

    public void timTheoMa(String ma) {
    System.out.println("\n--- Ket qua tim kiem theo Ma Phieu ---");
    boolean found = false;
    System.out.printf("%-10s %-10s %-12s %-25s %-15s\n",
            "MaPN", "MaNCC", "NgayNhap", "LoaiSPChung", "GhiChu");
    System.out.println("--------------------------------------------------------------------");
    for (PN p : ds) {
        if (p.getMaPhieu().equalsIgnoreCase(ma)) {
            p.xuat();
            found = true;
            break;
        }
    }
    if (!found) {
        System.out.println("\nKhong tim thay phieu nhap co ma " + ma);
    }
}

    // Tìm kiếm theo ngày
    public void timTheoNgay(String ngay) {
        System.out.println("\n--- Ket qua tim kiem theo Ngay Nhap ---");
        boolean found = false;
        System.out.printf("%-10s %-10s %-12s %-25s %-15s\n",
            "MaPN", "MaNCC", "NgayNhap", "LoaiSPChung", "GhiChu");
    System.out.println("--------------------------------------------------------------------");
        for (PN p : ds) {
            if (p.getNgayNhap().equals(ngay)) {
                p.xuat();
                found = true;
            }
        }
        if (!found) {
            System.out.println("\nKhong tim thay phieu nhap nao trong ngay " + ngay);
        }
    }

    // Thống kê số phiếu nhâp còn nợ theo ngày
    public void thongKePhieuNhapConNo(String ngay) {
    int soLuongConNo = 0;
    int tongSoPhieuTrongNgay = 0;
    // Đặt chuỗi so sánh tùy theo cách bạn nhập Ghi Chú
    final String TRANG_THAI_NO = "con no"; 
    for (PN p : ds) {
        if (p.getNgayNhap().equals(ngay)) {
            tongSoPhieuTrongNgay++;
            // Kiểm tra trạng thái nợ (so sánh không phân biệt chữ hoa/thường)
            if (p.getGhiChu().trim().toLowerCase().equals(TRANG_THAI_NO)) {
                soLuongConNo++;
            }
        }
    }
    System.out.println("\n=== Thong ke phieu nhap con no ngay " + ngay + " ===");
    if (tongSoPhieuTrongNgay == 0) {
        System.out.println("Khong co phieu nhap nao trong ngay " + ngay + ".");
    } else {
        System.out.println("- Tong so Phieu Nhap trong ngay: " + tongSoPhieuTrongNgay);
        System.out.println("- So luong Phieu Nhap CON NO: " + soLuongConNo);
    }
}
}