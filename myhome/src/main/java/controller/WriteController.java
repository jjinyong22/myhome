package controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logic.WriteCatalog;
import model.Condition;
import model.Writing;

@Controller
public class WriteController {
	@Autowired
	private WriteCatalog writeCatalog;
	
	@RequestMapping(value = "/write/writeReplyForm.html", method = RequestMethod.GET)
	public ModelAndView replyForm(Integer id,Integer parent_id,Integer group_id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing original = writeCatalog.getWritingById(id);//원글 검색
		original.setContent(null);//답글을 위해 비운다
		original.setWriter_name(null);//답글을 위해 비운다
		original.setEmail(null);//답글을 위해 비운다
		mav.addObject("title","RE]"+original.getTitle());//원글의 제목에 RE]를 붙인다
		mav.addObject("writing",original);
		mav.addObject("BODY","writeForm.jsp?group_id="+group_id+"&parent_id=" + parent_id);
		return mav;
	}

	@RequestMapping(value = "/write/updateDo.html", method = RequestMethod.POST)
	public ModelAndView updateDo(Writing writing, HttpSession session) {
		Writing old = writeCatalog.getWritingById(writing.getWriting_id());// 수정전의 정보를 검색
		ModelAndView mav = new ModelAndView("home/template");
		if (writing.getPassword().equals(old.getPassword())) {
			// 입력한 암호랑 디비암호랑 같은지 비교
			MultipartFile multiFile = writing.getImage();
			String fileName = multiFile.getOriginalFilename();
			if (fileName.equals("")) {// 이미지가 변경되지 않은경우
				writing.setImage_name(old.getImage_name());
			} else {// 변경된 경우
				String path = null;
				OutputStream os = null;
				fileName = multiFile.getOriginalFilename();
				ServletContext context = session.getServletContext();
				// 프로젝트를 관리하는 객체
				path = context.getRealPath("/upload/" + fileName);
				try {
					os = new FileOutputStream(path);
					BufferedInputStream bis = new BufferedInputStream(multiFile.getInputStream());
					byte[] buffer = new byte[8106];
					int read = 0;
					while ((read = bis.read(buffer)) > 0) {
						os.write(buffer, 0, read);
					} // 이미지 업로드
					if (os != null)
						os.close();
				} catch (Exception e) {
				}
				writing.setImage_name(fileName);// 새이미지이름 설정
			}
			writeCatalog.updateWriting(writing);// 디비 변경
			mav.addObject("BODY", "update_result_ok.jsp?id=" + writing.getWriting_id());
		} else {// 암호 일치하지 않을때
			mav.addObject("BODY", "update_result.jsp?id=" + writing.getWriting_id());
		}

		return mav;
	}

	@RequestMapping(value = "write/writeModify.html", method = RequestMethod.GET)
	public ModelAndView modifyForm(Integer id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = writeCatalog.getWritingById(id);
		mav.addObject(writing);
		mav.addObject("BODY", "update_form.jsp");
		return mav;
	}

	@RequestMapping(value = "write/deleteDo.html", method = RequestMethod.POST)
	public ModelAndView deleteDo(Writing writing) {
		Writing old = writeCatalog.getWritingById(writing.getWriting_id());// 기존글 검색
		ModelAndView mav = new ModelAndView("home/template");
		if (writing.getPassword().equals(old.getPassword())) {
			this.writeCatalog.deleteWriting(writing);// DB에서 삭제
			mav.addObject("BODY", "delete_result.jsp");
		} // 입력한 암호와 게시글의 암호가 일치하는 경우
		else {
			mav.addObject("BODY", "delete_result.jsp?id=" + writing.getWriting_id());
		}
		return mav;
	}

	@RequestMapping(value = "/write/writeDelete.html", method = RequestMethod.GET)
	public ModelAndView writeDelete(Integer id) {
		ModelAndView mav = new ModelAndView("home/template");
		Writing writing = this.writeCatalog.getWritingById(id);
		mav.addObject(writing);
		mav.addObject("BODY", "delete_form.jsp");
		return mav;
	}

	@RequestMapping(value = "/write/writeList.html")
	public ModelAndView writerList(Integer SEQNO, Integer PAGE_NUM) {
		if (PAGE_NUM == null)
			PAGE_NUM = 1;
		ModelAndView mav = new ModelAndView("home/template");
		if (SEQNO != null) {
			int rownum = writeCatalog.selectReplyPages(SEQNO);
			// 일련번호로 페이지 번호를 찾는다
			int page = rownum / 5;
			if (rownum % 5 != 0)
				page++;
			PAGE_NUM = page;
		}
		int currentPage = PAGE_NUM;
		int totalPageCount = 0;
		int startRow = 0;
		int endRow = 0;
		int count = writeCatalog.selectImageCount();// 전체글 갯수
		if (count > 0) {
			totalPageCount = count / 5;
			if (count % 5 > 0)
				totalPageCount++;
			startRow = (currentPage - 1) * 5 + 1;
			endRow = currentPage * 5;
			if (endRow > count)
				endRow = count;
		}
		Condition c = new Condition();
		c.setStartRow(startRow);
		c.setEndRow(endRow);
		List<Writing> writeList = writeCatalog.getWriting(c);
		mav.addObject("LIST", writeList);
		mav.addObject("count", count);
		mav.addObject("startRow", startRow);
		mav.addObject("endRow", endRow);
		mav.addObject("pageCount", totalPageCount);
		mav.addObject("currentPage", currentPage);
		mav.addObject("BODY", "list_view.jsp");
		return mav;
	}

	@RequestMapping(value = "/write/write.html", method = RequestMethod.POST)
	public ModelAndView write(@Valid Writing writing, BindingResult bindingResult, HttpSession session,
				Integer parent_id, Integer order_no, Integer group_id) {
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("home/template");
			mav.addObject("BODY", "writeForm.jsp");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		MultipartFile multiFile = writing.getImage();
		String fileName = null;
		String path = null;
		OutputStream os = null;
		if (multiFile != null) {// 업로드 이미지를 선택한 경우
			fileName = multiFile.getOriginalFilename();
			ServletContext context = session.getServletContext();
			path = context.getRealPath("/upload/" + fileName);
			try {
				os = new FileOutputStream(path);
				BufferedInputStream bis = new BufferedInputStream(multiFile.getInputStream());
				byte[] buffer = new byte[8196];
				int read = 0;
				while ((read = bis.read(buffer)) > 0) {
					os.write(buffer, 0, read);// 업로드함
				}
				if (os != null)
					os.close();
			} catch (Exception e) {
			}
			writing.setImage_name(fileName);// 파일이름설정
		}
		// DB에 삽입
		Integer maxId = writeCatalog.getMaxWritingid();
		if (maxId == null)
			maxId = 0;
		if (writing.getParent_id() == null) {// 원글일때
			writing.setParent_id(0);
			writing.setOrder_no(0);
			Integer gId = writeCatalog.selectMaxGroupId();
			if(gId == null) gId=0;
			writing.setGroup_id(gId + 1);
		} else {// 답글일때
			writing.setParent_id(parent_id);
			writing.setGroup_id(group_id);
			writing.setOrder_no(order_no);
			writeCatalog.updateOrderNoReply(writing);
		}
		writing.setWriting_id(maxId + 1);// 글번호1추가
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		String registerDate = year + "/" + month + "/" + date;
		writing.setRegister_date(registerDate);
		this.writeCatalog.insertWriting(writing);// 디비삽입
		ModelAndView mav = new ModelAndView("home/template");
		mav.addObject("BODY", "writeResult.jsp?SEQNO=" + (maxId + 1));
		return mav;
	}

	@RequestMapping(value = "/write/writeForm.html", method = RequestMethod.GET)
	public ModelAndView writeForm(HttpSession session) {
		String id = (String) session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("home/template");
		if (id == null) {
			mav.addObject("BODY", "writeFormLogin.jsp");
		} else {
			mav.addObject("BODY", "writeForm.jsp");
			mav.addObject(new Writing());// 객체주입 폼폼
		}
		return mav;
	}
}
