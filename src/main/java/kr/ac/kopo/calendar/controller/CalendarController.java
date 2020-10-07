package kr.ac.kopo.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalendarController {

	@GetMapping("/calendar")
	public String calendar() {
		
		return /* "/calendar/calendarAjax3"; */ "/calendar/calendar";
	}
	
//	@PostMapping("/calendar/add")
//	public String add(CalendarVO calendarVO) {
//		
//		System.out.println(calendarVO);
//		
//		return "/calendar/calendarAjax2"; /*"/calendar/calendar";*/
//	}
}
