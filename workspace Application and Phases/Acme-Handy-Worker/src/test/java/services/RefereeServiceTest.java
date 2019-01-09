
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
import domain.Box;
import domain.Complaint;
import domain.Note;
import domain.Referee;
import domain.Report;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RefereeServiceTest extends AbstractTest {

	//Service
	@Autowired
	private RefereeService	refereeService;
	@Autowired
	private ReportService	reportService;


	//Test 38.1
	@Test
	public void testCreate() {
		System.out.println("------Test Create------");
		super.authenticate("admin");
		final Referee referee, saved;
		final Collection<SocialProfile> sp1 = new ArrayList<>();
		final Collection<Box> boxes1 = new ArrayList<>();
		final Collection<Note> not = new ArrayList<>();
		final Collection<Report> repo = new ArrayList<>();
		final Collection<Complaint> comp = new ArrayList<>();
		referee = this.refereeService.create();
		try {
			//Actor
			referee.setName("Juan Francisco");
			referee.setEmail("juanfranciscolop@gmail.com");
			referee.setPhoneNumber("645783987");
			referee.setAddress("C/Belen Suarez");
			referee.setBan(false);
			referee.setMiddleName("Lopez");
			referee.setSurname("Fernandez");
			referee.setPhotoURL("http://www.juanfrlop.com");
			referee.setSocialProfiles(sp1);
			referee.setBoxes(boxes1);
			//Referee
			referee.setNotes(not);
			referee.setReports(repo);
			referee.setComplaints(comp);

			saved = this.refereeService.save(referee);
			Assert.isTrue(this.refereeService.findAll().contains(saved));
			super.unauthenticate();
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
	//Test 38.1
	@Test
	public void testSaveComplaint() {
		System.out.println("------Test SaveComplaint------");
		super.authenticate("referee1");
		final Referee saved;
		try {
			final ArrayList<Referee> ref = new ArrayList<>();
			ref.addAll(this.refereeService.findAll());
			final Referee newRef = ref.get(0);
			final Collection<Complaint> newComp = newRef.getComplaints();
			final ArrayList<Complaint> com = new ArrayList<>(newComp);
			final Complaint one = com.get(0);
			saved = this.refereeService.saveComplaint(newRef, one);
			Assert.notNull(saved);
			super.unauthenticate();

			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

	//Test 38.1
	@Test
	public void testFindByReport() {
		System.out.println("------Test FindByReport------");

		try {
			final ArrayList<Report> rep = new ArrayList<>();
			rep.addAll(this.reportService.findAll());
			final Report repor = rep.get(0);
			final Referee ref = this.refereeService.findByReport(repor);
			Assert.notNull(ref);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}

	}
}
