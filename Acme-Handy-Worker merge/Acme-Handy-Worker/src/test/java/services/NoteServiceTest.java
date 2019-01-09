
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
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Referee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	//Service----------------------------------------------
	@Autowired
	private NoteService			noteService;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private RefereeService		refereeService;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	//Test 35.2
	@Test
	public void testCreate() {
		System.out.println("------Test Create------");
		super.authenticate("customer");
		final Note note, saved;
		note = this.noteService.create();
		try {
			final Customer c = this.customerService.findByPrincipal();
			final ArrayList<Referee> refs = new ArrayList<>();
			refs.addAll(this.refereeService.findAll());
			final Referee ref = refs.get(0);
			note.setMoment(new Date());
			final ArrayList<HandyWorker> hwor = new ArrayList<>();
			hwor.addAll(this.handyWorkerService.findAll());
			final HandyWorker hw = hwor.get(0);
			note.setMoment(new Date());
			note.setMandatoryComment("comentario1");
			note.setRefereeComment("Comentario2");
			note.setHandyWorkerComment("Comentario3");
			note.setCustomerComment("Comentario 4");
			note.setCustomer(c);
			note.setReferee(ref);
			note.setHandyWorker(hw);
			saved = this.noteService.save(note);
			Assert.isTrue(this.noteService.findAll().contains(saved));
			super.unauthenticate();
			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
	//Test 35.3
	@Test
	public void testAddCommentCustomer() {
		System.out.println("------Test AddCommentCustomer------");
		super.authenticate("customer");
		final Note saved;

		try {
			Note note = new Note();
			final Customer c = this.customerService.findByPrincipal();
			final Collection<Note> notes = this.noteService.findAll();
			final ArrayList<Note> not = new ArrayList<>(notes);
			for (int i = 0; i < not.size(); i++)
				if (not.get(i).getCustomer().equals(c))
					note = not.get(i);
			System.out.println(note);

			saved = this.noteService.addCommentCustomer(note, "ejemplo1");
			System.out.println("1");
			Assert.notNull(saved);
			System.out.println("2");
			System.out.println(saved.getCustomerComment());
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}

	}

	//	//Test 35.3
	//	@Test
	//	public void testAddCommentReferee() {
	//		System.out.println("------Test AddCommentReferee------");
	//		super.authenticate("referee");
	//		final Note saved;
	//
	//		try {
	//			Note note = new Note();
	//			final Referee r = this.refereeService.findByPrincipal();
	//			final Collection<Note> notes = this.noteService.findAll();
	//			final ArrayList<Note> not = new ArrayList<>(notes);
	//			for (int i = 0; i < not.size(); i++)
	//				if (not.get(i).getReferee().equals(r))
	//					note = not.get(i);
	//			System.out.println(note);
	//
	//			saved = this.noteService.addCommentReferee(note, "ejemplo2");
	//			System.out.println("1");
	//			Assert.notNull(saved);
	//			System.out.println("2");
	//			System.out.println(saved.getRefereeComment());
	//			System.out.println("Success!");
	//
	//		} catch (final Exception e) {
	//			System.out.println("Error, " + e.getMessage() + "!");
	//		}
	//	}

	//Test 35.3
	@Test
	public void testAddCommentHandyWorker() {
		System.out.println("------Test AddCommentHandyWorker------");
		super.authenticate("handyWorker");
		final Note saved;

		try {
			Note note = new Note();
			final HandyWorker hw = this.handyWorkerService.findByPrincipal();
			final Collection<Note> notes = this.noteService.findAll();
			final ArrayList<Note> not = new ArrayList<>(notes);
			for (int i = 0; i < not.size(); i++)
				if (not.get(i).getHandyWorker().equals(hw))
					note = not.get(i);
			System.out.println(note);

			saved = this.noteService.addCommentHandyWorker(note, "ejemplo2");
			System.out.println("1");
			Assert.notNull(saved);
			System.out.println("2");
			System.out.println(saved.getHandyWorkerComment());
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
}
