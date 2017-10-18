package cm.ymsys.serivce;

import cn.ymsys.bean.Home;
import cn.ymsys.dao.HomeDao;

public class HomeService {
	HomeDao dao = new HomeDao();

	public Home findById(int id) {
		return dao.findById(id);
	}
}
