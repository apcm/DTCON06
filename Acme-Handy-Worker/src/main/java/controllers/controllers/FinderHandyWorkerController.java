
package controllers.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.FinderService;
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping("/finder/results/handyworker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	FinderService		finderService;

	@Autowired
	FixUpTaskService	fixUpTaskService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView result;
		final Finder finder = this.finderService.findByHWId(LoginService.getPrincipal().getId());
			//LoginService.getPrincipal().getId();
		final Collection<FixUpTask> fixUpTasks=finder.getFixUpTasks();
		result= new ModelAndView("finder/show");
		result.addObject("finder",finder);
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("requestURI", "finder/handyworker/show.do");
		return result;
		

	}
}
