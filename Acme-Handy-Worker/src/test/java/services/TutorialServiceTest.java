
package services;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class TutorialServiceTest extends AbstractTest {

	//Service
	@Autowired
	private TutorialService	tutorialService;


	//Tests
	@Test
	public void testCreateSaveAndFindAll() {
		System.out.println("------Test Create------");
		final Tutorial tut, saved;
		super.authenticate("handyWorker");

		tut = this.tutorialService.create();
		try {
			//			tut.setTitle("Tutorial1");
			//			tut.setLastUpdate(new Date());
			//			tut.setSummary("Sumario1");
			//			tut.setPhotoURL("http://www.urlphoto.com");
			//			tut.setSections(sec);
			final ArrayList<Tutorial> tu = new ArrayList<>();
			tu.addAll(this.tutorialService.findAll());
			final Tutorial tuto = tu.get(0);
			saved = this.tutorialService.saveForTest(tuto);
			Assert.isTrue(this.tutorialService.findAll().contains(saved));
			this.tutorialService.delete(saved);
			Assert.isNull(this.tutorialService.findOne(saved));
			super.unauthenticate();
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

	@Test
	public void testSave() {
		System.out.println("------Test Save------");
		try {
			super.authenticate("handyWorker");
			//final HandyWorker hw = this.handyWorkerService.findByPrincipal();
			final Tutorial tut = new Tutorial();
			tut.getHandyWorker();
			final ArrayList<Tutorial> tutos = new ArrayList<>();
			tutos.addAll(this.tutorialService.findAll());
			final Tutorial uno = tutos.get(0);
			uno.setTitle("TituloModificado");
			final Tutorial saved = this.tutorialService.save(uno);
			System.out.println(saved.getTitle());
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
}
