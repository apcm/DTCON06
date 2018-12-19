
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

import services.FinderService;
import controllers.AbstractController;
import domain.Finder;
import domain.FixUpTask;

@Controller
@RequestMapping("/finder/handyworker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private FinderService	finderService;


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
	public ModelAndView edit(@Valid final Finder finder, final BindingResult binding) {
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
		final String category;
		final String warranty;
		final Collection<FixUpTask> fixUpTasks;

		if (finder.getCategory() == null)
			category = null;
		else
			category = finder.getCategory();

		if (finder.getWarranty() == null)
			warranty = null;
		else
			warranty = finder.getWarranty();

		if (finder.getFixUpTasks().isEmpty())
			fixUpTasks = null;
		else
			fixUpTasks = finder.getFixUpTasks();

		res = new ModelAndView("finder/edit");
		res.addObject("category", category);
		res.addObject("warranty", warranty);
		res.addObject("fixUpTasks", fixUpTasks);
		res.addObject("message", messageCode);

		return res;
	}

}
