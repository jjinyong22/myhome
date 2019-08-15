package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;
@Repository
public class EntryDaoImpl implements EntryDao {
	@Autowired
	private SqlSession session;
	
	public void entryUser(User user) {
		session.insert("springmapper.putUser",user);
	}

}










