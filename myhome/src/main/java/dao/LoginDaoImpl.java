package dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SqlSession session;
	
	public User getUser(String id) {
		return session.selectOne("springmapper.getUser",id);//계정으로 사용자 검색
	}

	public String getPassword(String id) {
		return session.selectOne("springmapper.getPassword",id);	//계정으로 암호 검색
	}

	public Integer getIdCount(String id) {
		return session.selectOne("springmapper.checkId",id);	//동일한 계정의 갯수 검색
	}

}
