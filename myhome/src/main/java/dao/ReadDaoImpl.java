package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Bbs;
import model.Condition;
import model.Writing;

@Repository
public class ReadDaoImpl implements ReadDao {

	@Autowired
	private SqlSession session;
	
	
	
	public Writing readWriting(Integer id) {
		return session.selectOne("springmapper.getWritingInfoById",id);
	}

	public Integer selectReplyPags(Integer seqno) {
		return session.selectOne("springmapper.selectReplyPages",seqno);
	}

	public Integer selectImageCount() {
		return session.selectOne("springmapper.selectImageCount");
	}

	public List<Bbs> readBbs(Condition c) {
		return session.selectList("springmapper.getBBSList",c);
	}

	public Integer getBbsCount() {
		return session.selectOne("springmapper.getBBSCount");
	}

	public Bbs getBbsDetail(Integer id) {
		return session.selectOne("springmapper.getBBSDetail",id);
	}

}
