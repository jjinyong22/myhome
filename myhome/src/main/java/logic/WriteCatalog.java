package logic;

import java.util.List;

import model.Bbs;
import model.Condition;
import model.Writing;

public interface WriteCatalog {
	Integer getMaxBbsId();
	void putBbs(Bbs bbs);
	List<Bbs> readBbs(Condition c);
	Integer getBbsCount();
	Bbs getBbsDetail(Integer id);

	//이미지 게시글을 위한 메서드
		void updateWriting(Writing writing);//게시글 수정
		void deleteWriting(Writing writing);//삭제
		Writing getWritingById(Integer id);//글번호로 글정보 검색
		void insertWriting(Writing writing);//게시글 저장
		Integer getMaxWritingid();//최대 글번호 검색
		List<Writing> getWriting(Condition c);//글목록 검색
		
		Integer selectReplyPages(Integer seqno);//글번호로 일렬번호 찾으
		Integer selectImageCount();//게시글의 리턴 갯수

		Integer selectMaxGroupId(); //최대 그룹번호 검색
		void updateOrderNoReply(Writing writing);//답글의 순서번호 변경
}
