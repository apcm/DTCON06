
package controllers.handyWorker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.FinderService;
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping("/finder/handyworker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private FinderService	finderService;

	@Autowired
	FixUpTaskService		fixUpTaskService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView result;
		final Finder finder = this.finderService.findByHWId(LoginService.getPrincipal().getId());
		//LoginService.getPrincipal().getId();
		final Collection<FixUpTask> fixUpTasks = finder.getFixUpTasks();
		result = new ModelAndView("finder/show");
		result.addObject("finder", finder);
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("requestURI", "finder/handyworker/show.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int finderId) {
		final ModelAndView res;
		final Finder finder;

		finder = this.finderService.findOne(finderId);
		Assert.notNull(finder);
		res = this.createEditModelAndView(finder);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(finder);
		else
			try {
				this.finderService.save(finder);
				res = new ModelAndView("redirect:show.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(finder, "finder.commit.error");
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Finder finder) {
		ModelAndView res;

		res = this.createEditModelAndView(finder, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String messageCode) {
		ModelAndView res;
		final Collection<FixUpTask> fixUpTasks;

		if (finder.getFixUpTasks() == null)
			fixUpTasks = null;
		else
			fixUpTasks = finder.getFixUpTasks();

		res = new ModelAndView("finder/edit");
		res.addObject("fixUpTasks", fixUpTasks);
		res.addObject("message", messageCode);

		return res;
	}

}
