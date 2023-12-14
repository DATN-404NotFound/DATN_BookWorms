package com.poly.DATN_BookWorms.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class ViewCount implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			HttpSession session = request.getSession();
			Integer viewCount = (Integer) session.getAttribute("viewCount");

			if (viewCount == null) {
				viewCount = 1;
			} else {
				viewCount++;
			}
			LocalDate currentDate = LocalDate.now();

			// Lấy lượt xem theo ngày trong session
			Map<LocalDate, Integer> dailyViewCount = (Map<LocalDate, Integer>) session.getAttribute("dailyViewCount");

			// Lấy lượt xem của ngày trước đó
			Integer previousDayViewCount = (dailyViewCount != null) ? dailyViewCount.get(currentDate.minusDays(1))
					: null;

			session.setAttribute("viewCount", viewCount);
			session.setAttribute("previousDayViewCount", previousDayViewCount);
			modelAndView.addObject("viewCount", viewCount);
            modelAndView.addObject("previousDayViewCount", previousDayViewCount);

		}
	}
}