package logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ReadDao;
import dao.WriteDao;
import model.Bbs;
import model.Condition;
import model.Writing;

@Service
public class WriteCatalogImpl implements WriteCatalog {
	@Autowired
	private WriteDao writeDao;
	@Autowired
	private ReadDao readDao;
	
	
	public void updateOrderNoReply(Writing writing) {
		this.writeDao.updateOrderNoReply(writing);
	}

	public Integer selectMaxGroupId() {
		return this.writeDao.selectMaxGroupId();
	}

	public Integer selectReplyPages(Integer seqno) {
		return readDao.selectReplyPags(seqno);
	}

	public Integer selectImageCount() {
		return readDao.selectImageCount();
	}

	public void updateWriting(Writing writing) {
		this.writeDao.updateWriting(writing);
		
	}

	public void deleteWriting(Writing writing) {
		this.writeDao.deleteWriting(writing);
	}

	public Writing getWritingById(Integer id) {
		return this.writeDao.getWritingById(id);
	}

	public void insertWriting(Writing writing) {
		this.writeDao.insertWriting(writing);
	}

	public Integer getMaxWritingid() {
		return this.writeDao.getMaxWritingid();
	}

	public List<Writing> getWriting(Condition c) {
		return this.writeDao.getWriting(c);
	}

	public Integer getMaxBbsId() {
		return writeDao.getMaxId();
	}

	public void putBbs(Bbs bbs) {
		writeDao.insertBBS(bbs);
	}

	public List<Bbs> readBbs(Condition c) {
		return readDao.readBbs(c);
	}

	public Integer getBbsCount() {
		return readDao.getBbsCount();
	}

	public Bbs getBbsDetail(Integer id) {
		return readDao.getBbsDetail(id);
	}
	
	
}
