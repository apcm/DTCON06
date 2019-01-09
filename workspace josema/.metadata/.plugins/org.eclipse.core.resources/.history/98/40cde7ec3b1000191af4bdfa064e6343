
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyWorkerService;
import domain.Application;
import domain.Box;
import domain.Curriculum;
import domain.Finder;
import domain.HandyWorker;
import domain.Note;
import domain.Phase;
import domain.SocialProfile;

@Controller
@RequestMapping("/handyworker")
public class HandyWorkerController {

	@Autowired
	HandyWorkerService	handyWorkerService;


	// Constructor

	public HandyWorkerController() {
		super();
	}

	// Register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		HandyWorker handyWorker;

		handyWorker = this.handyWorkerService.create();
		res = this.createEditModelAndView(handyWorker);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final HandyWorker handyWorker, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(handyWorker);
		else
			try {
				this.handyWorkerService.saveForTest(handyWorker);
				result = new ModelAndView("redirect:welcome/index.do");
			} catch (final Throwable error) {
				result = this.createEditModelAndView(handyWorker, "handyWorker.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	// Ancillary methods
	protected ModelAndView createEditModelAndView(final HandyWorker handyWorker) {
		ModelAndView result;

		result = this.createEditModelAndView(handyWorker, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final HandyWorker handyWorker, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		Collection<SocialProfile> socialProfiles;
		Collection<Application> applications;
		Collection<Phase> plannedPhases;
		Finder finder;
		Collection<Note> notes;
		Curriculum curriculum;
		//final Collection<Endorsement> endorsements;

		boxes = handyWorker.getBoxes();
		socialProfiles = handyWorker.getSocialProfiles();
		applications = handyWorker.getApplications();
		plannedPhases = handyWorker.getPlannedPhases();
		finder = handyWorker.getFinder();
		notes = handyWorker.getNotes();
		curriculum = handyWorker.getCurriculum();
		//endorsements = customer.getEndorsements();

		result = new ModelAndView("handyworker/register");
		result.addObject("handyWorker", handyWorker);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("applications", applications);
		result.addObject("plannedPhases", plannedPhases);
		//	result.addObject("finder", finder);
		result.addObject("notes", notes);
		result.addObject("curriculum", curriculum);
		result.addObject("message", message);

		return result;
	}

}
