
package controllers.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import services.CustomisationService;
import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.Customisation;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("/finder/handyworker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	CustomisationService	customisationService;

	@Autowired
	FinderService			finderService;

	@Autowired
	HandyWorkerService		handyWorkerService;

	@Autowired
	FixUpTaskService		fixUpTaskService;

	@Autowired
	WarrantyService			warrantyService;

	@Autowired
	CategoryService			categoryService;


	public FinderHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView result;
		//Get the finder Duration from the db
		final List<Customisation> customisations = new ArrayList<Customisation>(this.customisationService.findAll());
		final Customisation customisationRN = customisations.get(customisations.size() - 1);
		final Integer finderDur = customisationRN.getFinderDuration();
		//Get the finder of the hw
		final HandyWorker handyWorker1 = this.handyWorkerService.findByPrincipal();
		final Finder finder = this.finderService.findByHWId(new Integer(handyWorker1.getId()));
		//Check if it is expired or not
		final Calendar fd = Calendar.getInstance();
		fd.setTime(finder.getMoment());
		fd.add(Calendar.HOUR, finderDur);

		if (Calendar.getInstance().getTime().after(fd.getTime()))
			finder.setKeyWord("expired12345678912345689123456789");

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
		result.addObject("keyWord", finder.getKeyWord());
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
				Collection<FixUpTask> fixUpTask = null;
				Calendar d1 = null;
				if (finder.getStartDate() != null && finder.getEndDate() != null)
					fixUpTask = this.fixUpTaskService.fixUpTaskFilterByRangeOfDates(finder.getStartDate(), finder.getEndDate());
				else if (finder.getStartDate() != null && finder.getEndDate() == null)
					fixUpTask = this.fixUpTaskService.fixUpTaskFilterByRangeOfDates(finder.getStartDate(), Calendar.getInstance().getTime());
				else if (finder.getStartDate() == null && finder.getEndDate() != null) {
					d1 = Calendar.getInstance();
					d1.setTime(new Date(2018 - 11 - 03));
					fixUpTask = this.fixUpTaskService.fixUpTaskFilterByRangeOfDates(d1.getTime(), finder.getEndDate());
				}
				if (finder.getMaxPrice() != null && finder.getMinPrice() != null)
					fixUpTask.retainAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfPrices(finder.getMinPrice().getAmount(), finder.getMaxPrice().getAmount()));
				else if (finder.getMaxPrice() == null && finder.getMinPrice() != null)
					fixUpTask.retainAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfPrices(finder.getMinPrice().getAmount(), 40000000000.0));
				else if (finder.getMaxPrice() != null && finder.getMinPrice() == null)
					fixUpTask.retainAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfPrices(0.0, finder.getMaxPrice().getAmount()));

				if (finder.getKeyWord() != null)
					fixUpTask.retainAll(this.fixUpTaskService.fixUpTaskFilterByKeyword(finder.getKeyWord()));
				if (finder.getCategory() != null)
					fixUpTask.retainAll(this.fixUpTaskService.fixUpTaskFilterByCategory(finder.getCategory().getName()));
				finder.setFixUpTasks(fixUpTask);
				finder.setMoment(Calendar.getInstance().getTime());
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
		res.addObject("message", messageCode);

		return res;
	}
}
