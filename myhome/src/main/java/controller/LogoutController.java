package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	@RequestMapping(value="/logout/template.html",
			method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("loginUser");
		session.removeAttribute("CART");
		ModelAndView mav = new ModelAndView("home/template");
		mav.addObject("BODY","logoutResult.jsp");
		return mav;
	}
}












