package com.poly.DATN_BookWorms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poly.DATN_BookWorms.entities.ViewWeb;
import com.poly.DATN_BookWorms.repositories.ViewWebRepo;
import com.poly.DATN_BookWorms.services.ViewWebService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ViewCount implements HandlerInterceptor {

	@Autowired
	ViewWebService viewWebService;
	@Autowired
	ViewWebRepo viewWebRepo;
	private final ViewWebRepo webRepo;

    public ViewCount(ViewWebRepo webRepo) {
        this.webRepo = webRepo;
    }

    public ViewCount() {
		this.webRepo = viewWebRepo;
		
	}

	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		String requestURI = request.getRequestURI();
    	if (requestURI.contains("/account/login") ||requestURI.contains("/Ibook/index") ) {
			// Thực hiện tăng số lượt xem và lưu vào cơ sở dữ liệu
			increaseViews();
		}
        // yourRepository.someQueryMethod();
    }

	private void increaseViews() {
		// Lấy đối tượng từ cơ sở dữ liệu (thay thế bằng logic lấy đối tượng của bạn)
		ViewWeb viewWeb = webRepo.findById(1).get();

		if (viewWeb != null) {
			// Lấy số lượt xem hiện tại từ đối tượng
			Integer currentView = viewWeb.getViews();
			
			// Nếu chưa có lượt xem, set là 1; ngược lại, tăng lên 1
			currentView = (currentView != null) ? currentView + 1 : 1;

			// Lưu số lượt xem mới vào đối tượng
			viewWeb.setViews(currentView);

			// Lưu đối tượng cập nhật vào cơ sở dữ liệu
			webRepo.save(viewWeb);
		}
	}
}