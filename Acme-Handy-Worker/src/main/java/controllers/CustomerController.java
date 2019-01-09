/*
 * CustomerController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Box;
import domain.Customer;
import domain.SocialProfile;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	CustomerService	customerService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}
	/*
	 * @RequestMapping(value = "/register", method = RequestMethod.GET)
	 * public ModelAndView register() {
	 * ModelAndView res;
	 * Customer customer;
	 * 
	 * customer = this.customerService.create();
	 * res = this.createEditModelAndView2(customer);
	 * return res;
	 * }
	 * 
	 * @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	 * public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
	 * ModelAndView res;
	 * 
	 * if (binding.hasErrors())
	 * res = this.createEditModelAndView2(customer);
	 * else
	 * try {
	 * this.customerService.save(customer);
	 * res = new ModelAndView("redirect:index.do");
	 * } catch (final Throwable oops) {
	 * res = this.createEditModelAndView3(customer, "customer.commit.error");
	 * }
	 * 
	 * return res;
	 * }
	 * 
	 * protected ModelAndView createEditModelAndView3(final Customer customer) {
	 * ModelAndView res;
	 * 
	 * res = this.createEditModelAndView3(customer, null);
	 * 
	 * return res;
	 * }
	 * 
	 * protected ModelAndView createEditModelAndView3(final Customer customer, final String messageCode) {
	 * ModelAndView res;
	 * 
	 * res = new ModelAndView("customer/edit");
	 * res.addObject("customer", customer);
	 * res.addObject("message", messageCode);
	 * 
	 * return res;
	 * }
	 * 
	 * protected ModelAndView createEditModelAndView2(final Customer customer) {
	 * ModelAndView res;
	 * 
	 * res = this.createEditModelAndView3(customer, null);
	 * 
	 * return res;
	 * }
	 * 
	 * protected ModelAndView createEditModelAndView2(final Customer customer, final String messageCode) {
	 * ModelAndView res;
	 * 
	 * res = new ModelAndView("customer/register");
	 * res.addObject("customer", customer);
	 * res.addObject("message", messageCode);
	 * 
	 * return res;
	 * }
	 * 
	 * @RequestMapping(value = "/edit", method = RequestMethod.GET)
	 * public ModelAndView edit(@RequestParam final int customerId) {
	 * ModelAndView res;
	 * Customer customer;
	 * 
	 * customer = this.customerService.findOne(customerId);
	 * Assert.notNull(customer);
	 * res = this.createEditModelAndView(customer);
	 * 
	 * return res;
	 * }
	 */
	//New version
	// Edition ------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Customer customer;

		customer = this.customerService.create();
		res = this.createEditModelAndView(customer);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(customer);
		else
			try {
				this.customerService.saveForTest(customer);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Handy-Worker");
			} catch (final Throwable error) {
				result = this.createEditModelAndView(customer, "customer.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final Customer customer) {
		ModelAndView result;

		result = this.createEditModelAndView(customer, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Customer customer, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		//final Collection<Endorsement> endorsements;

		boxes = customer.getBoxes();
		socialProfiles = customer.getSocialProfiles();
		//endorsements = customer.getEndorsements();

		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("customer/register");
		result.addObject("customer", customer);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);

		return result;
	}

}
