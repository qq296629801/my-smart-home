package cn.ymsys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.ymsys.bean.Home;
import cn.ymsys.dao.impl.HomeDaoImpl;
import cn.ymsys.util.Dbcon;

public class HomeDao implements HomeDaoImpl {
	Statement st;

	public Home findById(int id) {
		// TODO Auto-generated method stub
		st = Dbcon.getConnection();
		String sql = "select * from home where id=" + id;
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			Home h = new Home();
			if (rs.next()) {
				h.setId(rs.getInt("id"));
				h.setStatus(rs.getInt("status"));
				h.setName(rs.getString("name"));
			}
			return h;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Dbcon.close();
			e.printStackTrace();
		}
		return null;
	}

}
