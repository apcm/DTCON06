
package controllers.handyWorker;

import java.util.ArrayList;
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

import services.CategoryService;
import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("/finder/handyworker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private FinderService	finderService;

	@Autowired
	FixUpTaskService		fixUpTaskService;

	@Autowired
	WarrantyService			warrantyService;

	@Autowired
	CategoryService			categoryService;

	@Autowired
	HandyWorkerService		handyWorkerService;


	public FinderHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView result;
		final HandyWorker handyWorker1 = this.handyWorkerService.findByPrincipal();
		final Finder finder = this.finderService.findByHWId(new Integer(handyWorker1.getId()));
		//LoginService.getPrincipal().getId();
		final Collection<FixUpTask> fixUpTasks = new ArrayList<>();

		//		final Finder finder = new Finder();
		//		finder.setKeyWord("Hola amigos");
		//		finder.setCategory("puerta");
		//		finder.setEndDate(null);
		//		finder.setStartDate(null);
		//		final Collection<FixUpTask> fixUpTask = new ArrayList<FixUpTask>();
		//
		//		finder.setFixUpTasks(fixUpTask);
		//		finder.setId(3000);
		//		finder.setMaxPrice(new Money().newMoney(200, "euros"));
		//		finder.setMinPrice(new Money().newMoney(100, "euros"));
		//		finder.setVersion(3000);
		//		finder.setWarranty(new Warranty());

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
				// Esto es un prototipo para que se muestren Fix-up tasks, 
				// el método save debería introducirlas en el finder correctamente.
				finder.setFixUpTasks(this.fixUpTaskService.findAll());

				this.finderService.save(finder);
				res = new ModelAndView("redirect:show.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(finder, "finder.commit.error");
				System.out.println(oops.getMessage());
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
		Collection<FixUpTask> fixUpTasks;

		final Collection<Category> categories = this.categoryService.findAll();
		final Collection<Warranty> warranties = this.warrantyService.findAll();

		fixUpTasks = finder.getFixUpTasks();
		//if (fixUpTasks.isEmpty())
		//	fixUpTasks = null;
		res = new ModelAndView("finder/edit");
		res.addObject("finder", finder);
		res.addObject("categories", categories);
		res.addObject("warranties", warranties);
		//res.addObject("fixUpTasks", fixUpTasks);
		res.addObject("message", messageCode);

		return res;
	}
}
