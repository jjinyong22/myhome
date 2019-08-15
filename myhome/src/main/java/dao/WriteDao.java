package dao;

import java.util.List;

import model.Bbs;
import model.Condition;
import model.Writing;

public interface WriteDao {
	//게시글을 위한 메서드
	Integer getMaxId();
	void insertBBS(Bbs bbs);
	//이미지 게시글을 위한 메서드
	void updateWriting(Writing writing);//게시글 수정
	void deleteWriting(Writing writing);//삭제
	Writing getWritingById(Integer id);//글번호로 글정보 검색
	void insertWriting(Writing writing);//게시글 저장
	Integer getMaxWritingid();//최대 글번호 검색
	List<Writing> getWriting(Condition c);//글목록 검색
	Integer selectMaxGroupId();
	void updateOrderNoReply(Writing writing);
}
