package doan.single;

import java.util.Scanner;

public class NCC {
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    private String soDienThoai;
    private String tenSanPham;

    public NCC() {}

    public NCC(String maNCC, String tenNCC, String diaChi, String soDienThoai, String tenSanPham) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.tenSanPham = tenSanPham;
    }

    public NCC(NCC n) {
        this.maNCC = n.maNCC;
        this.tenNCC = n.tenNCC;
        this.diaChi = n.diaChi;
        this.soDienThoai = n.soDienThoai;
        this.tenSanPham = n.tenSanPham;
    }

    // Get/Set
    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }

    public String getTenNCC() { return tenNCC; }
    public void setTenNCC(String tenNCC) { this.tenNCC = tenNCC; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    // Hàm nhập
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma nha cung cap: "); maNCC = sc.nextLine();
        System.out.print("Nhap ten nha cung cap: "); tenNCC = sc.nextLine();
        System.out.print("Nhap dia chi: "); diaChi = sc.nextLine();
        System.out.print("Nhap so dien thoai: "); soDienThoai = sc.nextLine();
        System.out.print("Nhap ten san pham: "); tenSanPham = sc.nextLine();
    }

    // Hàm xuất
    public void xuat() {
        System.out.printf("%-10s %-20s %-20s %-15s %-15s\n",
                maNCC, tenNCC, diaChi, soDienThoai, tenSanPham);
    }
}