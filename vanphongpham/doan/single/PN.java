package doan.single;

import java.util.*;

public class PN {
    private String maPhieu;
    private String maNhaCungCap;
    private String ngayNhap;
    private String loaiSanPhamChung;
    private String ghiChu;

    // Hàm thiết lập 0 tham số
    public PN() {
    }

    // Hàm thiết lập có tham số
    public PN(String maPhieu, String maNhaCungCap, String ngayNhap, String loaiSanPhamChung,
            String ghiChu) {
        this.maPhieu = maPhieu;
        this.maNhaCungCap = maNhaCungCap;
        this.ngayNhap = ngayNhap;
        this.loaiSanPhamChung = loaiSanPhamChung;
        this.ghiChu = ghiChu;
    }

    // Hàm thiết lập sao chép
    public PN(PN p) {
        this.maPhieu = p.maPhieu;
        this.maNhaCungCap = p.maNhaCungCap;
        this.ngayNhap = p.ngayNhap;
        this.loaiSanPhamChung = p.loaiSanPhamChung;
        this.ghiChu = p.ghiChu;
    }

    // Get/set
    public String getMaPhieu() {return maPhieu;}
    public void setMaPhieu(String maPhieu) {this.maPhieu = maPhieu;}

    public String getMaNhaCungCap() {return maNhaCungCap;}
    public void setMaNhaCungCap(String maNhaCungCap) {this.maNhaCungCap = maNhaCungCap;}
    
    public String getNgayNhap() {return ngayNhap;}
    public void setNgayNhap(String ngayNhap) {this.ngayNhap = ngayNhap;}

    public String getLoaiSanPhamChung() {return loaiSanPhamChung;}
    public void setLoaiSanPhamChung(String loaiSanPhamChung) {this.loaiSanPhamChung = loaiSanPhamChung;}

    public String getGhiChu() {return ghiChu;}
    public void setGhiChu(String ghiChu) {this.ghiChu = ghiChu;}

    // Hàm nhập
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma phieu: ");
        maPhieu = sc.nextLine();

        System.out.print("Nhap ma nha cung cap: ");
        maNhaCungCap = sc.nextLine();

        System.out.print("Nhap ngay nhap (dd/mm/yyyy): ");
        ngayNhap = sc.nextLine();

        System.out.print("Nhap loai san pham chung: ");
        loaiSanPhamChung = sc.nextLine();

        System.out.print("Nhap Ghi Chu (Da tra/Con no): ");
        ghiChu = sc.nextLine();
    }

    // Hàm xuất
    public void xuat() {
        System.out.printf("%-10s %-10s %-12s %-25s %-15s\n",
             maPhieu, maNhaCungCap, ngayNhap, loaiSanPhamChung, ghiChu);
    }
}