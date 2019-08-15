package controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.WriteCatalog;
import model.Bbs;
import model.User;

@Controller
public class HomeController {
	
	@Autowired
	private WriteCatalog writeCatalog;
	
	@RequestMapping(value="/home/userentry.html",
			method=RequestMethod.GET)
	public ModelAndView entryForm() {
		ModelAndView mav = new ModelAndView("home/template");
		mav.addObject(new User());
		mav.addObject("BODY","userentry.jsp");
		return mav;
	}
	
	@RequestMapping(value="home/write.html",method=RequestMethod.POST)
	public ModelAndView put(@Valid Bbs bbs,BindingResult bindingResult, HttpSession session) {
		if(bindingResult.hasErrors()) {
		ModelAndView mav = new ModelAndView("home/template");
		mav.addObject("BODY","bbsInput.jsp");
		mav.getModel().putAll(bindingResult.getModel());
		return mav;
		}
		String id = (String)session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("home/template");
		if(id==null) {//로그인 안했을때
			mav.addObject("RESULT","nobody");
			mav.addObject(new User());  //폼을 위한 객체
			mav.addObject("BODY","nologin.jsp");
			return mav;
		}else {//로그인 한경우
			bbs.setId(id);
			//DB에 저장
			this.writeCatalog.putBbs(bbs);
			//리다이렉트로 바꿈
			return new ModelAndView("redirect:/read/read.html");//화면을 바꾼다
		}
	}
	
	
	@RequestMapping(value="home/bbsTemplate.html",method=RequestMethod.GET)
	public ModelAndView bbsTemp() {
		ModelAndView mav = new ModelAndView("home/template");
		mav.addObject(new Bbs());
		mav.addObject("BODY","bbsInput.jsp");
		return mav;
	}
	
	
	@RequestMapping(value="/home/intro.html",method=RequestMethod.GET)
	public ModelAndView intro(String BODY) {
		ModelAndView mav = new ModelAndView("home/template");
		mav.addObject("BODY",BODY);//바디를 데이터로 해서 지정해줘야함
		return mav;
	}
	
	@RequestMapping(value="/home/template.html",method=RequestMethod.GET)
	public ModelAndView template() {
		ModelAndView mav = new ModelAndView("home/template");
		return mav;
	}
}
