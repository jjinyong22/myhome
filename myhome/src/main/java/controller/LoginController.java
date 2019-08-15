package controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logic.LoginCatalog;
import model.Cart;
import model.CartItem;
import model.User;

@Controller
public class LoginController {
	
	@Autowired
	private LoginCatalog loginCatalog;
	@Autowired
	private Cart cart;
	
	@RequestMapping(value="/login/template.html",method=RequestMethod.POST)
	public ModelAndView login(@Valid User user,BindingResult bindingResult,HttpSession session) {
		ModelAndView mav = new ModelAndView("home/template");
		if(bindingResult.getErrorCount() > 0) {
			mav.addObject("HEADER","loginForm.jsp");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		String password =loginCatalog.getPwd(user.getId());
		if( password == null || !user.getPassword().equals(password)) {
			mav.addObject("BODY","loginResult.jsp");
		}else {
			session.setAttribute("loginUser", user.getId());
			List<CartItem> cartList = cart.getCart(user.getId());
			if(cartList != null) {
				Iterator it = cartList.iterator();
				int i = 0;
				while(it.hasNext()) {
					CartItem ci = (CartItem)it.next();
					this.cart.setCodeList(i, ci.getCode());
					this.cart.setNumList(i, ci.getNum());
					i++;
				}
				session.setAttribute("CART", cart);
			}
			mav.addObject("BODY","loginResult.jsp");
		}
		return mav;
	}
	
	@RequestMapping(value="/login/login.html",method=RequestMethod.GET)
	public ModelAndView toLogin() {
		ModelAndView mav = new ModelAndView("home/loginForm");
		mav.addObject(new User());//폼폼에 사용될 객체주입
		return mav;
	}
	
}
