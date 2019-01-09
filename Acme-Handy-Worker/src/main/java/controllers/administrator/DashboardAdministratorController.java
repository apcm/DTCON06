
package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CustomerService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.ReportService;
import controllers.AbstractController;
import domain.Customer;
import domain.HandyWorker;

@Controller
@RequestMapping("dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	ApplicationService	applicationService;

	@Autowired
	FixUpTaskService	fixUpTaskService;

	@Autowired
	CustomerService		customerService;

	@Autowired
	HandyWorkerService	hwService;

	@Autowired
	ReportService		reportService;


	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView res;
		res = new ModelAndView("/dashboard");
		//C-Level queries
		final ArrayList<Object> fixUpTaskPerUser = this.customerService.fixUpTasksStatistics();

		res.addObject("avgFixUpTask", fixUpTaskPerUser.get(0));
		res.addObject("minFixUpTask", fixUpTaskPerUser.get(1));
		res.addObject("maxFixUpTask", fixUpTaskPerUser.get(2));
		res.addObject("stdevFixUpTask", fixUpTaskPerUser.get(3));

		final ArrayList<Object> applicationsPerFixUpTask = this.fixUpTaskService.applicationsStatistics();

		res.addObject("avgApp", applicationsPerFixUpTask.get(0));
		res.addObject("minApp", applicationsPerFixUpTask.get(1));
		res.addObject("maxApp", applicationsPerFixUpTask.get(2));
		res.addObject("stdevApp", applicationsPerFixUpTask.get(3));

		final ArrayList<Object> pricePerFixUpTask = this.fixUpTaskService.maximunPriceStatistics();

		res.addObject("avgPrice", pricePerFixUpTask.get(0));
		res.addObject("minPrice", pricePerFixUpTask.get(1));
		res.addObject("maxPrice", pricePerFixUpTask.get(2));
		res.addObject("stdevPrice", pricePerFixUpTask.get(3));

		final ArrayList<Object> pricePerApplication = this.applicationService.offeredPriceStatisctics();

		res.addObject("avgOffPrice", pricePerApplication.get(0));
		res.addObject("minOffPrice", pricePerApplication.get(1));
		res.addObject("maxOffPrice", pricePerApplication.get(2));
		res.addObject("stdevOffPrice", pricePerApplication.get(3));

		final Double pendingApplicationsRatio = this.applicationService.pendingApplications();

		res.addObject("pendingApplication", pendingApplicationsRatio);

		final Double rejectedApplicationsRatio = this.applicationService.rejectedApplications();

		res.addObject("rejectedApplication", rejectedApplicationsRatio);

		final Double acceptedApplicationsRatio = this.applicationService.acceptedApplications();

		res.addObject("acceptedApplicationsRatio", acceptedApplicationsRatio);

		final Double elapsedApplicationsRatio = this.applicationService.elapsedApplications();

		res.addObject("elapsedApplicationsRatio", elapsedApplicationsRatio);

		final Collection<Customer> moreFixUpTasks = this.customerService.customersWithMoreFixUpTasks();

		res.addObject("moreFixUpTasks", moreFixUpTasks);

		final Collection<HandyWorker> moreApplications = this.hwService.handyWorkersWithMoreApplications();

		res.addObject("moreApplications", moreApplications);

		//B-Level queries
		final ArrayList<Object> complaintStatictics = this.fixUpTaskService.complaintsStatistics();

		res.addObject("avgComplaints", complaintStatictics.get(0));
		res.addObject("minComplaints", complaintStatictics.get(1));
		res.addObject("maxComplaints", complaintStatictics.get(2));
		res.addObject("stdevComplaints", complaintStatictics.get(3));

		final ArrayList<Object> noteStatictics = this.reportService.notesStatistics();

		res.addObject("avgNotes", noteStatictics.get(0));
		res.addObject("minNotes", noteStatictics.get(1));
		res.addObject("maxNotes", noteStatictics.get(2));
		res.addObject("stdevNotes", noteStatictics.get(3));

		final Double ratioComplaints = this.fixUpTaskService.fixUpTasksWithComplaints();

		res.addObject("ratioComplaints", ratioComplaints);

		final Collection<Customer> top3CustomerComplaints = this.customerService.topThreeCustomersbyComplaints();
		res.addObject("top1CustomerComplaints", top3CustomerComplaints);

		final Collection<HandyWorker> top3HandyWorkerComplaints = this.hwService.topThreeHandyWorkersByComplaints();
		res.addObject("top3HandyWorkerComplaints", top3HandyWorkerComplaints);
		return res;
	}
}
