
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

import services.FixUpTaskService;
import services.HandyWorkerService;
import services.PhaseService;
import controllers.AbstractController;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;

@Controller
@RequestMapping("/phase/handyworker")
public class PhaseHandyWorkerController extends AbstractController {

	@Autowired
	private PhaseService		phaseService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Constructor-----------------------------------------------------

	public PhaseHandyWorkerController() {
		super();
	}

	//Edit-------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int phaseId) {
		final ModelAndView result;
		Phase phase;

		phase = this.phaseService.findOne(phaseId);
		Assert.notNull(phase);
		result = this.createEditModelAndView(phase);

		return result;
	}
	//List------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final HandyWorker hw = this.handyWorkerService.findByPrincipal();
		final Collection<Phase> phases = this.phaseService.findByHandyWorkerId(new Integer(hw.getId()));

		result = new ModelAndView("phase/list");
		result.addObject("phases", phases);
		result.addObject("requestURI", "/phase/handyworker/list.do");

		return result;

	}

	//Save-------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Phase phase, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(phase);
		else
			try {
				this.phaseService.save(phase);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(phase, "phase.commit.error");
			}
		return result;
	}

	//Create-----------------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Phase phase;

		//final HandyWorker a = this.handyWorkerService.findByPrincipal();
		phase = this.phaseService.create();
		result = this.createEditModelAndView(phase);
		//result.addObject("handyWorkerId", a.getId());

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Phase phase, final BindingResult binding) {
		ModelAndView result;

		try {
			this.phaseService.delete(phase);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(phase, "phase.commit.error");
		}

		return result;
	}

	//ModelAndView-----------------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Phase phase) {
		ModelAndView result;

		result = this.createEditModelAndView(phase, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Phase phase, final String message) {
		ModelAndView result;
		Collection<Phase> phases;

		final HandyWorker hw = this.handyWorkerService.findByPrincipal();
		final Collection<FixUpTask> futs = this.fixUpTaskService.findByHandyWorkerId(new Integer(hw.getId()));
		phases = this.phaseService.findByHandyWorkerId(new Integer(hw.getId()));
		//final HandyWorker a = this.handyWorkerService.findByUserAccount(LoginService.getPrincipal());
		result = new ModelAndView("phase/edit");
		result.addObject("phase", phase);
		result.addObject("message", message);
		result.addObject("handyWorker", hw);
		result.addObject("fixUpTasks", futs);
		return result;
	}
}
