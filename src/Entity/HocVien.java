/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author truon
 */
public class HocVien {
    String MaHV;
    String MaKH;
    String MaNH;
    String Diem;

    public HocVien() {
    }

    public HocVien(String MaHV, String MaKH, String MaNH, String Diem) {
        this.MaHV = MaHV;
        this.MaKH = MaKH;
        this.MaNH = MaNH;
        this.Diem = Diem;
    }

    public String getMaHV() {
        return MaHV;
    }

    public void setMaHV(String MaHV) {
        this.MaHV = MaHV;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaNH() {
        return MaNH;
    }

    public void setMaNH(String MaNH) {
        this.MaNH = MaNH;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String Diem) {
        this.Diem = Diem;
    }
}
