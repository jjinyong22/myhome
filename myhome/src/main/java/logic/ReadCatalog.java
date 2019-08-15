package logic;

import java.util.List;

import model.Condition;
import model.Notice;
import model.Writing;

public interface ReadCatalog {
	Integer getMaxNoticeId();
	void putnotice(Notice notice);
	List<Notice> readNotice(Condition c);
	Integer getNoticeCount();
	Notice getNoticeDetail(Integer id);
	public Writing readWriting(Integer id);
}
