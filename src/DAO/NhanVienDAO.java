/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Doipass;
import Entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.XJdbc;

/**
 *
 * @author meoca
 */
public class NhanVienDAO extends EduSysDao<NhanVien, String> {

    String INSERT_SQL = "INSERT  INTO  NhanVien  (MaNV,  MatKhau,  HoTen,  VaiTro)  VALUES  (?,  ?,  ?,  ?)";
    String UPDATE_SQL = "UPDATE  NhanVien  SET  MatKhau=?,  HoTen=?,  VaiTro=?  WHERE  MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV=?";

    @Override
    public void insert(NhanVien enity) {
        try {
            XJdbc.update(INSERT_SQL,
                    enity.getMaNV(), enity.getMatKhau(), enity.getHoTen(), enity.isVaiTro());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien enity) {
        try {
            XJdbc.update(UPDATE_SQL,
                    enity.getMaNV(), enity.getMatKhau(), enity.getHoTen(), enity.isVaiTro());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NhanVien enity = new NhanVien();
                enity.setMaNV(rs.getString("MaNV"));
                enity.setMatKhau(rs.getString("MatKhau"));
                enity.setHoTen(rs.getString("HoTen"));
                enity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update1(Doipass model) throws SQLException {
        String sql = "UPDATE nhanvien SET Matkhau=? WHERE Manv=?";
        XJdbc.update(sql,
                model.getMatkhau(),
                model.getMaNV());
    }
}
