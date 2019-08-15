package dao;

import java.util.List;

import model.CartItem;

public interface CartDao {
	void insertCart(CartItem cartItem);//삽입
	void deleteCart(CartItem cartItem);//삭제
	void updateCart(CartItem cartItem);//변경
	Integer selectMaxCart();//가장 큰 일련번호
	List<CartItem> selectCart(String id);//지정된 ID의 상품목록
}
