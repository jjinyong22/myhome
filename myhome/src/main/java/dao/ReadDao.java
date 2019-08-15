package dao;

import java.util.List;

import model.Bbs;
import model.Condition;
import model.Writing;

public interface ReadDao {
	List<Bbs> readBbs(Condition c);//전체 게시글 검색(조건검색)  컨디션은 조건이 들어가는 객체
	Integer getBbsCount();//페이지 갯수 검색
	Bbs getBbsDetail(Integer id);//특정 게시글 검색
	Integer selectReplyPags(Integer seqno);
	Integer selectImageCount();
	Writing readWriting(Integer id);//글번호로 이미지 게시글 검색
}
