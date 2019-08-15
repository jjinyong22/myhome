package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Condition;
import model.Notice;
@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	private SqlSession session;
	
	public List<Notice> readBbs(Condition c) {
		return session.selectList("springmapper.getNOTICEList",c);
	}

	public Integer getNoticeCount() {
		return session.selectOne("springmapper.getNOTICECount");
	}

	public Notice getNoticeDetail(Integer id) {
		return session.selectOne("springmapper.getNOTICEDetail",id);
	}

}
