package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Condition;
import model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	@Autowired
	private SqlSession session;
	public Integer getItemCount() {
		return session.selectOne("springmapper.getItemCount");
	}

	public void putItem(Item item) {
		session.insert("springmapper.putItem",item);
	}

	public List<Item> getItems(Condition c) {
		return session.selectList("springmapper.getItemList",c);
	}

}
