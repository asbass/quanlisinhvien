/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.EduSysDao;
import Entity.KhoaHoc;
import java.util.List;
import Entity.ChuyenDe;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author HP
 */
public class KhoaHocDAO extends EduSysDao<KhoaHoc, String> {
    String INSERT_SQL ="INSERT  INTO  KhoaHoc  (MaKH,  MaCD,  HocPhi,  ThoiLuong,  NgayKG,  GhiChu, MaNV, NgayTao)  VALUES  (?,?,?,?,?,?,?,?)";
    String UPDATE_SQL ="UPDATE  KhoaHoc  SET  MaCD=?,  HocPhi=?,  ThoiLuong=?,  NgayKG=?,  GhiChu=?, MaNV=?, NgayTao=?  WHERE  MaKH=?";
    String DELETE_SQL ="DELETE FROM KhoaHoc WHERE MaKH=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL ="SELECT * FROM KhoaHoc where MaKH=?";
    @Override
    public void insert(KhoaHoc entity) {
    try {
            XJdbc.update(INSERT_SQL,
                    entity.getMaKH(),entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKG(),
                    entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao());
    }   catch (SQLException ex) {
            Logger.getLogger(ChuyenDeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public void update(KhoaHoc entity) {
        try {
            XJdbc.update(UPDATE_SQL,
                    entity.getMaKH(),entity.getMaCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getNgayKG(),
                    entity.getGhiChu(),entity.getMaNV(),entity.getNgayTao());        
        }   catch (SQLException ex) {
            Logger.getLogger(ChuyenDeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }    
    }

    @Override
    public void delete(String id) {
        try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public List<KhoaHoc> selectAll() {
       return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectById(String id) {
    List<KhoaHoc> list=this.selectBySQL(SELECT_BY_ID_SQL, id);
     if(list.isEmpty()){
         return null;
     }
     return list.get(0);    
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
         List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs= XJdbc.query(sql, args);
            while (rs.next()) {
                KhoaHoc etity = new KhoaHoc();
                etity.setMaKH(rs.getString("MaKH"));
                etity.setMaCD(rs.getString("MaCD"));
                etity.setHocPhi(rs.getDouble("HocPhi"));
                etity.setThoiLuong(rs.getInt("ThoiLuong"));
                etity.setNgayKG(rs.getDate("NgayKG"));
                etity.setGhiChu(rs.getString("GhiChu"));
                etity.setMaNV(rs.getString("MaNV"));
                etity.setNgayTao(rs.getDate("NgayTao"));
                list.add(etity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }    
    }
    
    public List<KhoaHoc> selectByChuyenDe(String macd){
        String sql = "SELECT * FROM KhoaHoc WHERE MaCD=?";
        return this.selectBySQL(sql, macd);
    }


}
