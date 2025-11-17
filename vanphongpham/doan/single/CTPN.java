package doan.single;

import java.util.*;

public class CTPN {
    private String maCTPN;
    private String maPhieu;
    private String tenHang;
    private int soLuong;
    private double donGia;

    // Hàm thiết lập
    public CTPN() {}

    public CTPN(String maCTPN, String maPhieu, String tenHang, int soLuong, double donGia) {
        this.maCTPN = maCTPN;
        this.maPhieu = maPhieu;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    public CTPN(CTPN ct) {
        this.maCTPN = ct.maCTPN;
        this.maPhieu = ct.maPhieu;
        this.tenHang = ct.tenHang;
        this.soLuong = ct.soLuong;
        this.donGia = ct.donGia;
    }
    // Get/Set
    public String getMaCTPN() { return maCTPN; }
    public void setMaCTPN(String maCTPN) { this.maCTPN = maCTPN; }

    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }

    public String getTenHang() { return tenHang; }
    public void setTenHang(String tenHang) { this.tenHang = tenHang; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public double getThanhTien() {
        return soLuong * donGia;
    }

    public void nhap(Scanner sc) {
        System.out.print("Nhap ma chi tiet phieu nhap: ");
        maCTPN = sc.nextLine();
        System.out.print("Nhap ma phieu nhap: ");
        maPhieu = sc.nextLine();
        System.out.print("Nhap ten hang: ");
        tenHang = sc.nextLine();
        System.out.print("Nhap so luong: ");
        try {
            soLuong = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Loi: So luong khong hop le, dat mac dinh la 0.");
            soLuong = 0;
        }
        System.out.print("Nhap don gia: ");
        try {
            donGia = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Loi: Don gia khong hop le, dat mac dinh la 0.0.");
            donGia = 0.0;
        }
    }
    public void xuat() {
        System.out.printf("%-10s %-10s %-15s %-8d %-10.2f %-10.2f\n",
        maCTPN, maPhieu, tenHang, soLuong, donGia, getThanhTien());
    }
}