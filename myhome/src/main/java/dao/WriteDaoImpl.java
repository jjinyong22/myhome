package dao;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Bbs;
import model.Condition;
import model.Writing;

@Repository
public class WriteDaoImpl implements WriteDao {
	
	@Autowired
	private SqlSession session;
	
	
	public void updateOrderNoReply(Writing writing) {
		session.update("springmapper.updateOrderNoReply",writing);
	}

	public Integer selectMaxGroupId() {
		return session.selectOne("springmapper.selectMaxGroupId");
	}

	public void updateWriting(Writing writing) {
		session.update("springmapper.updateWritingInfo",writing);
	}

	public void deleteWriting(Writing writing) {
		session.delete("springmapper.deleteWritingInfo",writing);
	}

	public Writing getWritingById(Integer id) {
		return session.selectOne("springmapper.getWritingInfoById",id);
	}

	public void insertWriting(Writing writing) {
		session.insert("springmapper.insertWriting",writing);
	}
	
	public Integer getMaxWritingid() {
		return session.selectOne("springmapper.getMaxWritingId");
	}

	public List<Writing> getWriting(Condition c) {
		return session.selectList("springmapper.getWritingInfo",c);
	}

	public Integer getMaxId() {
		Integer seqno = session.selectOne("springmapper.getMaxBbsId");
		if(seqno == null) seqno = 0;
		return seqno;
	}

	public void insertBBS(Bbs bbs) {
		bbs.setSeqno(getMaxId() + 1);
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		String bbsDate = year+"/"+month+"/"+date;
		bbs.setBbs_date(bbsDate);
		session.insert("springmapper.putBBS",bbs);
	}

}
