/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
 * @author meoca
 */
public class ChuyenDeDAO extends EduSysDao<ChuyenDe, String> {

    String INSERT_SQL = "INSERT  INTO  ChuyenDe  (MaCD,  TenCD,  HocPhi,  ThoiLuong,  Hinh,  MoTa)  VALUES  (?,  ?, ?,  ?,  ?,  ?)";
    String UPDATE_SQL = "UPDATE  ChuyenDe  SET  TenCD=?,  HocPhi=?,  ThoiLuong=?,  Hinh=?,  MoTa=?  WHERE  MaCD=?";
    String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
    String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe where MaCD=?";

    @Override
    public void insert(ChuyenDe enity) {
        try {
            XJdbc.update(INSERT_SQL,
                    enity.getMaCD(), enity.getTenCD(), enity.getHocPhi(), enity.getThoiLuong(), enity.getHinh(), enity.getMoTa());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ChuyenDe enity) {
        try {
            XJdbc.update(UPDATE_SQL, enity.getTenCD(), enity.getHocPhi(), enity.getThoiLuong(), enity.getHinh(), enity.getMoTa(), enity.getMaCD());
        } catch (SQLException ex) {
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
    public List<ChuyenDe> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<ChuyenDe>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                ChuyenDe enity = new ChuyenDe();
                enity.setMaCD(rs.getString("MaCD"));
                enity.setTenCD(rs.getString("TenCD"));
                enity.setHocPhi(rs.getDouble("HocPhi"));
                enity.setThoiLuong(rs.getInt("ThoiLuong"));
                enity.setHinh(rs.getString("hinh"));
                enity.setMoTa(rs.getString("mota"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
