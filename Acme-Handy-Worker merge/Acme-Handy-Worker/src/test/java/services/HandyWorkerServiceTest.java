
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

import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Application;
import domain.Box;
import domain.Curriculum;
import domain.Customisation;
import domain.Endorsement;
import domain.Finder;
import domain.HandyWorker;
import domain.Note;
import domain.Phase;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class HandyWorkerServiceTest extends AbstractTest {

	//Service
	@Autowired
	private HandyWorkerService		handyWorkerService;

	@Autowired
	private CurriculumService		curriculumService;

	@Autowired
	private FinderService			finderService;

	@Autowired
	private CustomisationService	customisationService;


	//Test
	@Test
	public void testHandyWorker() {
		System.out.println("------Test HandyWorker------");
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
			//			hw.setUserAccount(new UserAccount());
			hw.setBoxes(boxes);
			hw.setEndorsements(ends);
			hw.setScore(2);
			hw.setMake("make");
			hw.setApplications(app);
			hw.setPlannedPhases(pph);
			final ArrayList<Finder> finds = new ArrayList<>();
			finds.addAll(this.finderService.findAll());
			final Finder fin = finds.get(0);
			hw.setFinder(fin);
			hw.setNotes(notes);
			final ArrayList<Curriculum> curs = new ArrayList<>();
			curs.addAll(this.curriculumService.findAll());
			final Curriculum cur = curs.get(1);
			hw.setCurriculum(cur);

			super.authenticate("handyWorker");

			saved = this.handyWorkerService.saveForTest(hw);
			Assert.isTrue(this.handyWorkerService.findAll().contains(saved));
			super.unauthenticate();

			super.authenticate("admin");
			final Collection<HandyWorker> hwwmapps = this.handyWorkerService.handyWorkersWithMoreApplications();
			Assert.notNull(hwwmapps);

			final Collection<HandyWorker> topthreehw = this.handyWorkerService.topThreeHandyWorkersByComplaints();
			Assert.notNull(topthreehw);

			super.unauthenticate();
			super.authenticate("handyWorker");
			//save
			System.out.println("Test save");
			final UserAccount uaHw = LoginService.getPrincipal();
			System.out.println(uaHw);
			final HandyWorker hwForSave = this.handyWorkerService.findByUserAccount(uaHw);
			System.out.println(hwForSave);
			hwForSave.setName("ejemplo");
			final HandyWorker savedHw = this.handyWorkerService.save(hwForSave);
			Assert.isTrue(this.handyWorkerService.findAll().contains(savedHw));
			System.out.println(this.handyWorkerService.findByPrincipal().getName());

			//11.2?
			//37.2?

			super.unauthenticate();
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

	@Test
	public void saveScoreTest() {
		System.out.println("------------Test saveScore------------");
		super.authenticate("admin");
		try {
			final ArrayList<HandyWorker> hws = new ArrayList<>();
			hws.addAll(this.handyWorkerService.findAll());
			final HandyWorker hw = hws.get(0);

			final ArrayList<Customisation> cuss = new ArrayList<>();
			cuss.addAll(this.customisationService.findAll());
			final Customisation cus = cuss.get(0);
			System.out.println("Negative words: " + cus.getNegativeWords());
			System.out.println("Positive words: " + cus.getPositiveWords());

			final ArrayList<Endorsement> ends = new ArrayList<>();
			ends.addAll(hw.getEndorsements());
			final Endorsement end = ends.get(0);
			end.setComment("bad fantastic excellent great");
			System.out.println("Comment: " + end.getComment());

			System.out.println("Actual score: " + hw.getScore());
			final HandyWorker savedScore = this.handyWorkerService.saveScore(hw, cus);
			System.out.println("New score: " + savedScore.getScore());

			Assert.isTrue(savedScore.getScore().equals(2));

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

}
