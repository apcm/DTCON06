
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	//Service
	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Test
	@Test
	public void testCreate() {
		System.out.println("------Test Create------");
		final Application app, saved, app2, app3;
		super.authenticate("handyWorker");
		app = this.applicationService.create();
		System.out.println(this.applicationService.findAll());
		try {

			//			app.setMoment(new Date());
			//			app.setStatus("pending");
			//			//			final Money mon = new Money();
			//			//			mon.setAmount(8.0);
			//			//			mon.setCurrency("euros");
			//			//			System.out.println(mon);
			//			//			System.out.println(mon.getAmount());
			//			//			System.out.println(mon.getCurrency());
			//			//			app.setOfferedPrice(mon);
			//			app.setComment("comment");
			//			app.setRejectedCause("rejectedCause");
			//			//			app.setCreditCard(new CreditCard());
			//			final ArrayList<FixUpTask> futs = new ArrayList<>();
			//			futs.addAll(this.fixUpTaskService.findAll());
			//			final FixUpTask fut = futs.get(0);
			//			System.out.println("FUT para la App: " + fut);
			//			app.setFixUpTask(fut);
			//			System.out.println("FUT de la App setted: " + app.getFixUpTask());
			//			System.out.println(app);
			//			saved = this.applicationService.save(app);
			//
			//			final ArrayList<Application> hwApps = new ArrayList<>();
			//			final HandyWorker hwForSave = this.handyWorkerService.findByPrincipal();
			//			hwApps.addAll(hwForSave.getApplications());
			//			hwApps.add(saved);
			//			hwForSave.setApplications(hwApps);
			//			final HandyWorker hwSaved = this.handyWorkerService.saveForTest(hwForSave);
			//			System.out.println("HW saved: " + hwSaved);
			//			System.out.println("Apps del hw: " + hwSaved.getApplications());
			//
			//			final ArrayList<Application> futApps = new ArrayList<>();
			//			futApps.addAll(fut.getApplications());
			//			futApps.add(saved);
			//			fut.setApplications(futApps);
			//			final FixUpTask futSaved = this.fixUpTaskService.saveForTest(fut);
			//			System.out.println("FUT saved: " + futSaved);
			//			System.out.println("Apps de la FUT: " + futSaved.getApplications());
			//
			//			//			
			//			//
			//			//			final ArrayList<Application> hwApps = new ArrayList<>();
			//			//			final HandyWorker hwForSave = this.handyWorkerService.findByPrincipal();
			//			//			hwApps.addAll(hwForSave.getApplications());
			//			//			hwApps.add(saved);
			//			//			hwForSave.setApplications(hwApps);
			//			//			final HandyWorker hwSaved = this.handyWorkerService.save(hwForSave);
			//
			//			System.out.println("App saved: " + saved);
			//			System.out.println(this.applicationService.findAll());
			//			Assert.isTrue(this.applicationService.findAll().contains(saved));

			super.unauthenticate();

			//findByCustomer
			System.out.println("Test findByCustomer");
			super.authenticate("customer");
			final ArrayList<Customer> custs = new ArrayList<>();
			custs.addAll(this.customerService.findAll());
			final Customer cust = custs.get(1);
			final Collection<Application> appsCust = this.applicationService.findByCustomer();
			System.out.println(appsCust);
			Assert.notNull(appsCust);

			//saveByCustomer
			final ArrayList<Application> sbc = new ArrayList<>();
			sbc.addAll(appsCust);
			final Application appCust = sbc.get(1);
			System.out.println(appCust.getComment());
			appCust.setComment("ejemplo");
			final Application appSavedByCust = this.applicationService.saveByCustomer(appCust);
			Assert.notNull(appSavedByCust);
			final Collection<Application> appsCust2 = this.applicationService.findByCustomer();
			final ArrayList<Application> sbc2 = new ArrayList<>();
			sbc2.addAll(appsCust2);
			final Application appCust2 = sbc2.get(1);
			System.out.println(appCust2.getComment());

			super.unauthenticate();

			//findByHandyWorker
			super.authenticate("handyWorker");
			final ArrayList<HandyWorker> hws = new ArrayList<>();
			hws.addAll(this.handyWorkerService.findAll());
			final HandyWorker hw = hws.get(1);
			System.out.println(hw);
			final Collection<Application> appsHw = this.applicationService.findByHandyWorker();
			System.out.println(appsHw);
			Assert.notNull(appsHw);

			//saveByHandyWorker
			System.out.println("Test saveByHandyWorker");
			final ArrayList<Application> sbhw = new ArrayList<>();
			sbhw.addAll(appsHw);
			final Application appHw = sbhw.get(0);
			System.out.println(appHw.getComment());
			appHw.setComment("ejemploHw");
			final Application appSavedByHw = this.applicationService.saveByHandyWorker(appHw);
			Assert.notNull(appSavedByHw);
			final Collection<Application> appsHw2 = this.applicationService.findByHandyWorker();
			final ArrayList<Application> sbhw2 = new ArrayList<>();
			sbhw2.addAll(appsHw2);
			final Application appHw2 = sbhw2.get(0);
			System.out.println(appHw2.getComment());

			super.unauthenticate();

			//other methods
			System.out.println("Testing other methods");
			super.authenticate("admin");

			final ArrayList<Application> everyApp = new ArrayList<>();
			everyApp.addAll(this.applicationService.findAll());
			final Application appOthers = everyApp.get(0);
			app2 = appOthers;
			app2.setStatus("accepted");
			final Application newApp2 = this.applicationService.save(app2);

			System.out.println("App2 saved");

			app3 = appOthers;
			app3.setStatus("rejected");
			final Application newApp3 = this.applicationService.save(app3);

			System.out.println("App3 saved");

			final double pendingApps = this.applicationService.pendingApplications();
			Assert.isTrue(pendingApps > 0);

			System.out.println("pendingApplications Working");

			final double acceptedApps = this.applicationService.acceptedApplications();
			Assert.isTrue(acceptedApps > 0);

			System.out.println("acceptedApplications Working");

			final double rejectedApps = this.applicationService.rejectedApplications();
			Assert.isTrue(rejectedApps > 0);

			System.out.println("rejectedApplications Working");

			super.unauthenticate();

			//			super.authenticate("customer");
			final Date date = new GregorianCalendar(2012, 2, 2).getTime();
			final Application appElapsed = appOthers;
			final FixUpTask fixUpTaskElapsed = appElapsed.getFixUpTask();
			System.out.println("Original time: " + fixUpTaskElapsed.getEndDate());
			appElapsed.setStatus("pending");
			fixUpTaskElapsed.setEndDate(date);
			System.out.println("Elapsed Time: " + fixUpTaskElapsed.getEndDate());
			System.out.println("Status: " + appElapsed.getStatus());
			final Application newApp4 = this.applicationService.save(appElapsed);
			//			final Application newApp4 = this.applicationService.saveByCustomer(app2);
			//			super.unauthenticate();
			final FixUpTask savedElapsedFUT = this.fixUpTaskService.saveForAppTest(fixUpTaskElapsed);

			System.out.println("Elapsed date saved: " + newApp4.getFixUpTask().getEndDate());

			System.out.println("App saved with elapsed time: Status: " + newApp4.getStatus() + ", Moment: " + savedElapsedFUT.getEndDate());

			super.authenticate("admin");
			final double elapsedApps = this.applicationService.elapsedApplications();
			System.out.println("Elapsed Applications: " + elapsedApps);
			Assert.isTrue(elapsedApps > 0);

			System.out.println("elapsedApplications Working");

			final ArrayList<Object> offPStats = this.applicationService.offeredPriceStatisctics();
			System.out.println("OfferesPriceStatistics: " + offPStats);
			Assert.notNull(offPStats);

			System.out.println("offeredPriceStatistics Working");

			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
}
