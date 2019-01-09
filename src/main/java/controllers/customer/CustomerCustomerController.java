
package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.CustomerService;
import domain.Box;
import domain.Customer;
import domain.Endorsement;
import domain.FixUpTask;
import domain.SocialProfile;

@Controller
@RequestMapping("/customer/customer")
public class CustomerCustomerController {

	@Autowired
	CustomerService	customerService;


	// Constructors -----------------------------------------------------------

	public CustomerCustomerController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Customer customer;

		customer = this.customerService.findByPrincipal();
		//customer = this.customerService.findOne(customerId);
		res = this.createEditEditModelAndView(customer);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(customer);
		else
			try {
				this.customerService.save(customer);
				result = new ModelAndView("redirect:http://localhost:8080/Acme-Handy-Worker");
			} catch (final Throwable error) {
				result = this.createEditEditModelAndView(customer, "customer.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Customer customer) {
		ModelAndView result;

		result = this.createEditEditModelAndView(customer, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Customer customer, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		final Collection<Endorsement> endorsements;
		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		fixUpTasks = customer.getFixUpTasks();
		boxes = customer.getBoxes();
		socialProfiles = customer.getSocialProfiles();
		endorsements = customer.getEndorsements();
		userAccount = customer.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("customer/edit");
		result.addObject("customer", customer);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		result.addObject("endorsements", endorsements);
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////SHOW//////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Customer customer;

		customer = this.customerService.findByPrincipal();
		//customer = this.customerService.findOne(customerId);
		res = this.createShowModelAndView(customer);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Customer customer) {
		ModelAndView result;

		result = this.createShowModelAndView(customer, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Customer customer, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		final Collection<Endorsement> endorsements;
		final Collection<FixUpTask> fixUpTasks;
		UserAccount userAccount;

		fixUpTasks = customer.getFixUpTasks();
		boxes = customer.getBoxes();
		socialProfiles = customer.getSocialProfiles();
		endorsements = customer.getEndorsements();
		userAccount = customer.getUserAccount();
		//		 if (socialProfiles.isEmpty())
		//		 * socialProfiles = null;
		//		 * if (endorsements.isEmpty())
		//		 * endorsements = null;
		//if (boxes.isEmpty())
		//	boxes = null;

		result = new ModelAndView("customer/show");
		result.addObject("customer", customer);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		result.addObject("endorsements", endorsements);
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("userAccount", userAccount);
		return result;
	}

}
