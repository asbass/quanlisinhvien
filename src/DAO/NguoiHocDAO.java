/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NguoiHoc;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ntc10
 */
public class NguoiHocDAO extends EduSysDao<NguoiHoc, String> {
    String INSERT_SQL = "INSERT INTO NguoiHoc(MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ?, NgayDK = ? WHERE MaNH = ?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH = ?";

    @Override
    public void insert(NguoiHoc entity) {
        try {
            XJdbc.update(INSERT_SQL,
                    entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK().toString());
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @Override
    public void update(NguoiHoc entity) {
        try {
            XJdbc.update(UPDATE_SQL,
                    entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh().toString(), entity.getGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK().toString());
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String key) {
        try {
            XJdbc.update(DELETE_SQL, key);
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectById(String key) {
        List<NguoiHoc> list = this.selectBySQL(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
                List<NguoiHoc> list = new ArrayList<NguoiHoc>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getString("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("ghiChu"));
                entity.setNgayDK(rs.getString("NgayDK"));
                entity.setMaNV(rs.getString("MaNV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
    public List<NguoiHoc> selectByKeyword(String keyword){
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH LIKE ?";
        return this.selectBySQL(sql,"%" + keyword + "%");
    }

}
