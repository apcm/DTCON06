
package controllers.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.FixUpTaskService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.FixUpTask;
import domain.Warranty;

@Controller
@RequestMapping("/fixUpTask/customer")
public class CustomerFixUpTaskController extends AbstractController {

	@Autowired
	FixUpTaskService	fixUpTaskService;

	@Autowired
	CategoryService		categoryService;

	@Autowired
	WarrantyService		warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Collection<FixUpTask> fixUpTasks;

		fixUpTasks = this.fixUpTaskService.findByCustomer();
		res = new ModelAndView("fixUpTask/list");
		res.addObject("fixUpTasks", fixUpTasks);
		res.addObject("requestURI", "fixUpTask/customer/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int id) {
		final ModelAndView res;
		FixUpTask fixUpTask;

		fixUpTask = this.fixUpTaskService.findOne(id);
		res = this.createEditModelAndView(fixUpTask);
		return res;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView res;
		FixUpTask fixUpTask;

		fixUpTask = this.fixUpTaskService.create();

		res = this.createEditModelAndView(fixUpTask);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int id) {
		final ModelAndView res;
		FixUpTask fixUpTask;

		fixUpTask = this.fixUpTaskService.findOne(id);
		res = this.createEditModelAndView(fixUpTask);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final FixUpTask fixUpTask, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(fixUpTask);
		else
			try {
				this.fixUpTaskService.save(fixUpTask);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(fixUpTask, "fixUpTask.commit.error");
			}
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final FixUpTask fixUpTask, final BindingResult binding) {
		ModelAndView res;

		try {
			this.fixUpTaskService.delete(fixUpTask);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(fixUpTask, "fixUpTask.commit.error");
		}

		return res;
	}
	//Ancillary methods
	protected ModelAndView createEditModelAndView(final FixUpTask fixUpTask) {
		ModelAndView res;
		res = this.createEditModelAndView(fixUpTask, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final FixUpTask fixUpTask, final String messageCode) {
		final ModelAndView res;
		Collection<Category> categories;
		final Collection<Warranty> warranties;
		List<Warranty> warrantiesAll;

		warrantiesAll = new ArrayList<Warranty>();

		categories = this.categoryService.findAll();
		warrantiesAll.addAll(this.warrantyService.findAll());

		for (final Warranty w : warrantiesAll)
			if (!w.isFinalMode())
				warrantiesAll.remove(w);

		warranties = warrantiesAll;

		res = new ModelAndView("fixUpTask/edit");
		res.addObject("fixUpTask", fixUpTask);
		res.addObject("category", categories);
		res.addObject("warranties", warranties);
		res.addObject("message", messageCode);

		return res;
	}
}
