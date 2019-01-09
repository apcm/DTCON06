
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
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class PhaseServiceTest extends AbstractTest {

	//Service----------------------------------------------
	@Autowired
	private PhaseService		phaseService;
	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Test----------------------------------------------

	@Test
	public void testCreate() {
		System.out.println("----testCreate()----");
		super.authenticate("handyWorker");
		final Phase phase = this.phaseService.create();
		final Phase saved;
		try {
			phase.setTitle("tituloPhase");
			phase.setDescription("Phase description");
			phase.setStartMoment(new Date());
			phase.setEndMoment(new Date());
			phase.setNumber(21);
			final ArrayList<FixUpTask> fixx = new ArrayList<>();
			fixx.addAll(this.fixUpTaskService.findAll());
			final FixUpTask uno = fixx.get(0);
			phase.setFixUpTask(uno);
			saved = this.phaseService.saveForTest(phase);
			Assert.isTrue(this.phaseService.findAll().contains(saved));
			//Falta método findAll.
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
			final HandyWorker hw = this.handyWorkerService.findByPrincipal();
			final Collection<Phase> p = hw.getPlannedPhases();
			final ArrayList<Phase> pArr = new ArrayList<>(p);
			final Phase uno = pArr.get(0);
			final ArrayList<Phase> phases = new ArrayList<>();
			phases.addAll(this.phaseService.findAll());
			System.out.println(uno.getTitle());
			uno.setTitle("TituloModificado");
			final Phase saved = this.phaseService.save(uno);
			System.out.println(saved.getTitle());
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

	@Test
	public void testFindByHandyWorker() {
		System.out.println("------Test FindByHandyWorker------");
		super.authenticate("handyWorker");
		try {
			final Collection<Phase> ph = this.phaseService.findByHandyWorker();
			Assert.notNull(ph);
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	//	@Test
	//	public void testDelete() {
	//		System.out.println("------Test Delete------");
	//		try {
	//			final ArrayList<Phase> phases = new ArrayList<>();
	//			phases.addAll(this.phaseService.findAll());
	//			final Phase uno = phases.get(0);
	//
	//			this.phaseService.delete(uno);
	//			Assert.isTrue(!this.phaseService.findAll().contains(this.phaseService.findOne(uno)));
	//		} catch (final Exception e) {
	//			System.out.println("Error, " + e.getMessage() + "!");
	//		}
	//		super.unauthenticate();
	//
	//	}
}
