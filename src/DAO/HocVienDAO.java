/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.EduSysDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.XJdbc;
import Entity.HocVien;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author truon
 */
public class HocVienDAO extends EduSysDao<HocVien, String>{
     String INSERT_SQL ="INSERT  INTO  HocVien  (MaHV,  MaKH,  MaNH,  Diem)  VALUES  (?,  ?,  ?,  ?)";
    String UPDATE_SQL ="UPDATE  HocVien  SET  MaKH=?,  MaNH=?,  Diem=?  WHERE  MaNH=?";
    String DELETE_SQL ="DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL ="SELECT * FROM HocVien WHERE MaHV=?";

    @Override
    public void insert(HocVien enity) {
         try {
             XJdbc.update(UPDATE_SQL,
                     enity.getMaHV(),enity.getMaKH(),enity.getMaNH(),enity.getDiem());
         } catch (SQLException ex) {
             Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void update(HocVien enity) {
         try {
             XJdbc.update(UPDATE_SQL,
                     enity.getMaHV(),enity.getMaKH(),enity.getMaNH(),enity.getDiem());
         } catch (SQLException ex) {
             Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void delete(String id) {
         try {
             XJdbc.update(DELETE_SQL, id);
         } catch (SQLException ex) {
             Logger.getLogger(HocVienDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectById(String id) {
        List<HocVien> list=this.selectBySQL(SELECT_BY_ID_SQL, id);
     if(list.isEmpty()){
         return null;
     }
     return list.get(0);
    }
    public List<HocVien> selectByKhoaHoc(String MaKH){
        String sql="select * from HocVien where MaKH=?";
        return this.selectBySQL(sql, MaKH);
    }

    public List<HocVien> selectByChuyenDe(String maCD){
        String sql="select * from HocVien where MaCD=?";
        return this.selectBySQL(sql, maCD);
    }

    
    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<HocVien>();
        try {
            ResultSet rs= XJdbc.query(sql, args);
            while (rs.next()) {
                HocVien enity = new HocVien();
                enity.setMaHV(rs.getString("MaHV"));
                enity.setMaKH(rs.getString("MaKH"));
                enity.setMaNH(rs.getString("MaNH"));
                enity.setDiem(rs.getString("Diem"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }
}
