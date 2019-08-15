package logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.NoticeDao;
import dao.ReadDao;
import model.Condition;
import model.Notice;
import model.Writing;

@Service
public class ReadCatalogImpl implements ReadCatalog {

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private ReadDao readDao;
	
	public Writing readWriting(Integer id) {
		return readDao.readWriting(id);
	}
	
	public Integer getMaxNoticeId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void putnotice(Notice notice) {
		// TODO Auto-generated method stub

	}

	public List<Notice> readNotice(Condition c) {
		return noticeDao.readBbs(c);
	}

	public Integer getNoticeCount() {
		return noticeDao.getNoticeCount();
	}

	public Notice getNoticeDetail(Integer id) {
		return noticeDao.getNoticeDetail(id);
	}

}
