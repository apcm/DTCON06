
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Box;
import domain.Curriculum;
import domain.Customer;
import domain.Endorsement;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Note;
import domain.Phase;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	//Service
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private CurriculumService	curriculumService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private FinderService		finderService;


	//Test 8.1 para Customer
	@Test
	public void testCreateCustomer() {
		System.out.println("------Test CreateCustomer------");
		final Customer customer, saved;
		final Collection<SocialProfile> sp1 = new ArrayList<>();
		final Collection<Box> boxes1 = new ArrayList<>();
		final Collection<Endorsement> end1 = new ArrayList<>();
		final Collection<FixUpTask> fix1 = new ArrayList<>();

		customer = this.customerService.create();

		try {
			//Actor
			customer.setName("Juan Francisco");
			customer.setEmail("juanfranciscolop@gmail.com");
			customer.setPhoneNumber("645783987");
			customer.setAddress("C/Belen Suarez");
			customer.setBan(false);
			customer.setMiddleName("Lopez");
			customer.setSurname("Fernandez");
			customer.setPhotoURL("http://www.juanfrlop.com");
			customer.setSocialProfiles(sp1);
			customer.setBoxes(boxes1);
			//Endorser
			customer.setEndorsements(end1);
			customer.setScore(65);
			//Customer
			customer.setFixUpTasks(fix1);
			super.authenticate("customer");
			saved = this.customerService.save(customer);
			Assert.isTrue(this.customerService.findAll().contains(saved));
			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

	//Test 8.1 para HandyWorker
	@Test
	public void testCreateHandyWorker() {
		System.out.println("------Test CreateHandyWorker------");
		final HandyWorker hw, saved;
		final Collection<Application> app = new ArrayList<>();
		final Collection<Phase> pph = new ArrayList<>();
		final Collection<Note> notes = new ArrayList<>();
		final Collection<Box> boxes = new ArrayList<>();
		final Collection<SocialProfile> sps = new ArrayList<>();
		final Collection<Endorsement> ends = new ArrayList<>();

		hw = this.handyWorkerService.create();

		try {
			hw.setName("Maria");
			hw.setEmail("maria@gmail.com");
			hw.setPhoneNumber("123456788");
			hw.setAddress("address");
			hw.setBan(false);
			hw.setMiddleName("mnmaria");
			hw.setSurname("surnameMaria");
			hw.setPhotoURL("http://www.urlphoto.com");
			hw.setSocialProfiles(sps);
			hw.setBoxes(boxes);
			hw.setEndorsements(ends);
			hw.setScore(2);
			hw.setMake("make");
			hw.setApplications(app);
			hw.setPlannedPhases(pph);
			final ArrayList<Finder> find = new ArrayList<>();
			find.addAll(this.finderService.findAll());
			final Finder f = find.get(1);
			hw.setFinder(f);
			hw.setNotes(notes);
			final ArrayList<Curriculum> curs = new ArrayList<>();
			curs.addAll(this.curriculumService.findAll());
			final Curriculum cur = curs.get(1);
			hw.setCurriculum(cur);
			super.authenticate("handyWorker");
			saved = this.handyWorkerService.saveForTest(hw);
			Assert.isTrue(this.handyWorkerService.findAll().contains(saved));
			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
	//Test 11.1
	@Test
	public void testFindByFixUpTask() {
		System.out.println("------Test FindByFixUpTask------");
		super.authenticate("handyWorker");
		try {
			final ArrayList<FixUpTask> fixUp = new ArrayList<>();
			fixUp.addAll(this.fixUpTaskService.findAll());
			final FixUpTask fixCu = fixUp.get(0);
			final Customer cus = this.customerService.findByFixUpTask(fixCu);
			Assert.notNull(cus);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}
	//Test 12.5
	@Test
	public void testFixUpTasksStatistics() {
		System.out.println("------Test FixUpTasksStatistics------");
		super.authenticate("admin");
		try {
			final ArrayList<Object> ar = this.customerService.fixUpTasksStatistics();
			Assert.notNull(ar.get(0));
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	//Test 12.5
	@Test
	public void testCustomersWithMoreFixUpTasks() {
		System.out.println("------Test CustomersWithMoreFixUpTasks------");
		super.authenticate("admin");
		try {
			final Collection<Customer> cust = this.customerService.customersWithMoreFixUpTasks();
			Assert.notNull(cust);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	//Test 38.5
	@Test
	public void testTopThreeCustomersbyComplaints() {
		System.out.println("------Test TopThreeCustomersbyComplaints------");
		super.authenticate("admin");
		try {
			final Collection<Customer> cust = this.customerService.topThreeCustomersbyComplaints();
			Assert.notNull(cust);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}
}
