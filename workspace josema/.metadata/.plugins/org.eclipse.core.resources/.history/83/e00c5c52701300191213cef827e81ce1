
package controllers.administrator;

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
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	@Autowired
	private CategoryService	categoryService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Category> categories;

		categories = this.categoryService.findAll();

		res = new ModelAndView("category/list");
		res.addObject("categories", categories);
		res.addObject("requestURI", "category/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {
				this.categoryService.save(category);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(category, "category.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Category category;

		category = this.categoryService.create();

		res = this.createEditModelAndView(category);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		Category category;

		category = this.categoryService.findOne(categoryId);
		Assert.notNull(category);
		result = this.createEditModelAndView(category);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Category category, final BindingResult binding) {
		ModelAndView result;

		try {
			this.categoryService.delete(category);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(category, "category.commit.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category) {
		ModelAndView result;

		result = this.createEditModelAndView(category, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Category category, final String message) {
		ModelAndView result;
		Collection<Category> parentCategories;
		parentCategories = this.categoryService.findAll();

		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("parentCategories", parentCategories);
		result.addObject("message", message);

		return result;
	}
}
