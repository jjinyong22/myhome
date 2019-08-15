package dao;

import org.springframework.web.bind.annotation.RequestMapping;

import model.User;

public interface LoginDao {
	User getUser(String id);
	String getPassword(String id);
	Integer getIdCount(String id);
}
