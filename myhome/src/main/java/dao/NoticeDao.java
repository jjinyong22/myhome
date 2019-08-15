package dao;

import java.util.List;

import model.Condition;
import model.Notice;

public interface NoticeDao {
	List<Notice> readBbs(Condition c);//전체 게시글 검색(조건검색)  컨디션은 조건이 들어가는 객체
	Integer getNoticeCount();//페이지 갯수 검색
	Notice getNoticeDetail(Integer id);//특정 게시글 검색
}
