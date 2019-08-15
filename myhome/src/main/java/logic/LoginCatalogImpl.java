package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.EntryDao;
import dao.LoginDao;
import model.User;

@Service
public class LoginCatalogImpl implements LoginCatalog {

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private EntryDao entryDao;

	public void entryUser(User user) {
		entryDao.entryUser(user);
	}
	
	public String getPwd(String id) {
		return loginDao.getPassword(id);
	}

	public Integer getIdCnt(String id) {
		return loginDao.getIdCount(id);
	}

}
