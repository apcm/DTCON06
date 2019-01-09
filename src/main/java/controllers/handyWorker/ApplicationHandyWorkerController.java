
package controllers.handyWorker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import controllers.AbstractController;
import domain.Application;
import domain.FixUpTask;

@Controller
@RequestMapping("/application/handyWorker")
public class ApplicationHandyWorkerController extends AbstractController {

	@Autowired
	ApplicationService	applicationService;

	@Autowired
	FixUpTaskService	fixUpTaskService;

	@Autowired
	HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<Application> applications;

		applications = this.applicationService.findByHandyWorker();

		res = new ModelAndView("application/list");
		res.addObject("applications", applications);
		res.addObject("requestURI", "application/handyWorker/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int fixUpTaskId) {
		final ModelAndView res;
		final Application application;
		final FixUpTask fixUpTask;

		application = this.applicationService.create();
		fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);
		application.setFixUpTask(fixUpTask);

		res = this.createEditModelAndView(application);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application app, final BindingResult b) {
		ModelAndView res;
		if (b.hasErrors())
			res = this.createEditModelAndView(app);
		else
			try {
				this.applicationService.save(app);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(app, "application.commit.error");
			}
		return res;
	}
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int applicationId) {
		ModelAndView res;
		Application application;

		application = this.applicationService.findOne(applicationId);
		res = this.createEditModelAndView(application);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView res;
		res = this.createEditModelAndView(application, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		final ModelAndView res;
		res = new ModelAndView("application/edit");
		res.addObject("application", application);
		res.addObject("message", messageCode);

		return res;
	}
}
