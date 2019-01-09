
package controllers.handyWorker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.FixUpTask;

@Controller
@RequestMapping("fixUpTask/handyWorker")
public class HandyWorkerFixUpTaskController extends AbstractController {

	@Autowired
	FixUpTaskService	fixUpTaskService;

	@Autowired
	CustomerService		customerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<FixUpTask> fixUpTasks;

		fixUpTasks = this.fixUpTaskService.findAll();
		res = new ModelAndView("fixUpTask/list");
		res.addObject("fixUpTasks", fixUpTasks);
		res.addObject("requestURI", "fixUpTask/handyWorker/list.do");

		return res;
	}

	/*
	 * @RequestMapping(value = "/list", method = RequestMethod.GET)
	 * public ModelAndView customerList(@RequestParam final int customerId) {
	 * final ModelAndView res;
	 * final Collection<FixUpTask> fixUpTasks;
	 * final Customer c;
	 * 
	 * c = this.customerService.findOne(customerId);
	 * fixUpTasks = c.getFixUpTasks();
	 * res = new ModelAndView("fixUpTask/list");
	 * res.addObject("fixUpTasks", fixUpTasks);
	 * res.addObject("requestURI", "fixUpTask/handyWorker/list.do");
	 * 
	 * return res;
	 * }
	 */
}
