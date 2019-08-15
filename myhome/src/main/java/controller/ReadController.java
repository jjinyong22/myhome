package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.ItemCatalog;
import logic.ReadCatalog;
import logic.WriteCatalog;
import model.Bbs;
import model.Condition;
import model.Item;
import model.Writing;

@Controller
public class ReadController {
	@Autowired
	private WriteCatalog writeCatalog;
	@Autowired
	private ItemCatalog itemCatalog;
	@Autowired
	private ReadCatalog readCatalog;

	@RequestMapping(value = "/read/readImage.html")
	public ModelAndView readImage(Integer id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = this.readCatalog.readWriting(id);
		mav.addObject("writing", writing);
		mav.addObject("BODY", "read_image.jsp");
		return mav;
	}

	@RequestMapping(value = "/read/product.html", method = RequestMethod.GET)
	public ModelAndView readItems(Integer pageNo) {
		ModelAndView mav = new ModelAndView("home/template");
		Integer cnt = itemCatalog.getItemCount();
		if (cnt == null)
			cnt = 0;
		int startRow = 0;
		int endRow = 0;
		int pageCnt = 0;
		int currentPage = 0;
		if (pageNo == null)
			currentPage = 1;
		else
			currentPage = pageNo;
		if (cnt > 0) {
			pageCnt = cnt / 5;
			if (cnt % 5 > 0)
				pageCnt++;
			startRow = (currentPage - 1) * 5 + 1;
			endRow = currentPage * 5;
			if (endRow > cnt)
				endRow = cnt;
		}
		Condition c = new Condition();
		c.setStartRow(startRow);
		c.setEndRow(endRow);
		List<Item> itemList = itemCatalog.getItems(c);
		mav.addObject("ITEM_LIST", itemList);
		mav.addObject("COUNT", pageCnt);
		mav.addObject("BODY", "itemListView.jsp");
		return mav;
	}

	@RequestMapping(value = "read/readDetail.hrml", method = RequestMethod.GET)
	public ModelAndView readDetail(Integer SEQNO) {
		// SEQNO로 디비 검색
		// 검색 결과를 ModelAndView에 저장
		ModelAndView mav = new ModelAndView("home/template");
		Bbs bbs = writeCatalog.getBbsDetail(SEQNO);
		mav.addObject("BBS_ITEM", bbs);
		mav.addObject("BODY", "bbsItemView.jsp");
		return mav;
	}

	@RequestMapping(value = "/read/read.html", method = RequestMethod.GET)
	public ModelAndView readBbs(Integer pageNo) {
		ModelAndView mav = new ModelAndView("home/template");// 페이지 갯수를 받는다
		// 페이지 갯수를 찾는다
		Integer cnt = writeCatalog.getBbsCount();
		if (cnt == 0)
			cnt = 0;
		int startRow = 0;
		int endRow = 0;
		int pageCnt = 0;
		int currentPage = 0;
		if (pageNo == null)
			currentPage = 1;
		else
			currentPage = pageNo;
		if (cnt > 0) {// 글의 갯수가 0보다 큰 경우
			pageCnt = cnt / 5;
			if (cnt % 5 > 0)
				pageCnt++;// 페이지 갯수 계산
			startRow = (currentPage - 1) * 5 + 1;
			endRow = currentPage * 5;
			if (endRow > cnt)
				endRow = cnt;
		}
		Condition c = new Condition();
		c.setStartRow(startRow);
		c.setEndRow(endRow);
		// 다섯개의 게시글을 찾는다.
		List<Bbs> bbsList = writeCatalog.readBbs(c);
		// 검색된 결과를 mav에 저장
		mav.addObject("BBS_LIST", bbsList);// 검색된 게시글 저장
		mav.addObject("COUNT", pageCnt);// 페이지 갯수저장
		mav.addObject("BODY", "bbsListView.jsp");
		return mav;
	}
}
