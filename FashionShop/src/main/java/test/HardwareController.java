package test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HardwareController {
	@Autowired
	private HardwareService hardwareService;
	
	@RequestMapping(value="/test/list", method=RequestMethod.GET )
	public ModelAndView getList() {
		List testList = hardwareService.selectAll();
		ModelAndView mav = new ModelAndView("test/list");
		mav.addObject("testList",testList);
		
		return mav;
	}
	
}
