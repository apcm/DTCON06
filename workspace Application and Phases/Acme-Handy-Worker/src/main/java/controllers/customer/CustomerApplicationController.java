
package controllers.customer;

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
import controllers.AbstractController;
import domain.Application;
import domain.FixUpTask;

@Controller
@RequestMapping("application/customer")
public class CustomerApplicationController extends AbstractController {

	@Autowired
	ApplicationService	applicationService;

	@Autowired
	FixUpTaskService	fixUpTaskService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int fixUpTaskId) {
		final ModelAndView res;
		final Collection<Application> applications;
		final FixUpTask fixuptask;

		fixuptask = this.fixUpTaskService.findOne(fixUpTaskId);
		applications = fixuptask.getApplications();
		res = new ModelAndView("application/list");
		res.addObject("applications", applications);
		res.addObject("requestURI", "application/customer/list.do");

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		final ModelAndView res;
		Application app;

		app = this.applicationService.findOne(applicationId);

		res = this.createEditModelAndView(app);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(application);
		else
			try {
				this.applicationService.save(application);
				final int fixUpTaskId = application.getFixUpTask().getId();
				res = new ModelAndView("redirect:list.do?fixUpTaskId=" + fixUpTaskId);
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(application, "announcement.commit.error");
			}
		return res;
	}

	//Ancillary methods
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
