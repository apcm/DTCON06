
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import utilities.TickerGenerator;
import domain.Complaint;
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	//Service
	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Test 35.1
	@Test
	public void testCreate() {
		System.out.println("------Test Create------");
		super.authenticate("customer");
		final Complaint complaint, saved;
		final Collection<String> attach = new ArrayList<>();

		attach.add("http://www.attachmentent1.com");
		complaint = this.complaintService.create();

		try {
			complaint.setAttachments(attach);
			complaint.setDescription("The door is still broken.");
			complaint.setMoment(new Date());
			complaint.setTicker(TickerGenerator.generateTicker());

			final ArrayList<FixUpTask> futs = new ArrayList<>();
			super.authenticate("handyWorker");
			futs.addAll(this.fixUpTaskService.findAll());
			final FixUpTask fut = futs.get(0);
			complaint.setFixUpTask(fut);

			saved = this.complaintService.saveForTest(complaint);
			Assert.isTrue(this.complaintService.findAll().contains(saved));
			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
	//Test 35.1
	@Test
	public void testSaveByCustomer() {
		System.out.println("------Test SaveByCustomer------");
		super.authenticate("customer");
		try {
			final ArrayList<Complaint> comp = new ArrayList<>();
			comp.addAll(this.complaintService.findByCustomer());
			final Complaint c = comp.get(0);
			c.setDescription("Esto es un cambio de descripcion");
			final Complaint saved = this.complaintService.saveByCustomer(c);
			Assert.notNull(saved);
			final ArrayList<Complaint> comp2 = new ArrayList<>();
			comp2.addAll(this.complaintService.findByCustomer());
			final Complaint c2 = comp2.get(0);
			System.out.println(c2.getDescription());
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
	//Test 35.1
	@Test
	public void testFindByCustomer() {
		System.out.println("------Test FindByCustomer------");
		super.authenticate("customer");
		try {
			final Collection<Complaint> sus = this.complaintService.findByCustomer();
			Assert.notNull(sus);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	//Test 36.1
	@Test
	public void testFindComplaintsWithNoReferee() {
		System.out.println("------Test FindComplaintsWithNoReferee------");
		super.authenticate("referee1");
		try {
			final Collection<Complaint> sus = this.complaintService.findNoReferee();
			Assert.notNull(sus);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	//Test 36.2
	@Test
	public void testFindComplaintsWithReferee() {
		System.out.println("------Test FindComplaintsWithReferee------");
		super.authenticate("referee1");
		try {
			final Collection<Complaint> sus = this.complaintService.findByReferee();
			Assert.notNull(sus);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	//Test 37.3
	@Test
	public void testFindHandyWorker() {
		System.out.println("------Test FindComplaintsWithReferee------");
		super.authenticate("handyWorker");
		try {
			final Collection<Complaint> sus = this.complaintService.findByHandyWorker();
			Assert.notNull(sus);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}
}
