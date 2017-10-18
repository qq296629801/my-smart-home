package cn.ymsys.dao;

import java.sql.SQLException;
import java.sql.Statement;

import cn.ymsys.bean.Notice;
import cn.ymsys.util.Dbcon;

public class NoticeDao {
	Statement st;

	public void save(Notice n) {
		// TODO Auto-generated method stub
		st = Dbcon.getConnection();
		String sql = "insert into notice(name,home_id) value('" + n.getName() + "'," + n.getHome_id() + ")";
		try {
			int a = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Dbcon.close();
			e.printStackTrace();
		}
	}
}
