
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Referee;

@Service
@Transactional
public class NoteService {

	//Repositories
	@Autowired
	public NoteRepository		noteRepository;

	//Services
	@Autowired
	public ComplaintService		complaintService;
	@Autowired
	public RefereeService		refereeService;

	public ReportService		reportService;

	@Autowired
	public CustomerService		customerService;
	@Autowired
	public HandyWorkerService	handyWorkerService;


	//Constructor
	public NoteService() {
		super();
	}

	//35.2
	public Note create() {

		//Logged user must be a customer
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.CUSTOMER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//TODO: Revisar create (Hace falta meter el ticker?)
		final Note res = new Note();
		res.setCustomer(new Customer());
		res.setHandyWorker(new HandyWorker());
		res.setReferee(new Referee());
		res.setCustomerComment("");
		res.setHandyWorkerComment("");
		res.setRefereeComment("");
		res.setMandatoryComment("");
		res.setMoment(new Date());
		res.setCustomerComment("");
		return res;
	}

	public Note save(final Note note) {
		Assert.notNull(note);

		//Logged user must be a customer/referee/handyworker
		final Authority a = new Authority();
		final Authority b = new Authority();
		final Authority c = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.CUSTOMER);
		b.setAuthority(Authority.REFEREE);
		c.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//Note's owner is logged customer/referee/handyworker
		//Assert.isTrue(this.customerService.findByPrincipal().equals(note.getCustomer()) || this.refereeService.findByPrincipal().equals(note.getReferee()) || this.handyWorkerService.findByPrincipal().equals(note.getHandyWorker()));

		final Note res = note;

		res.setMoment(Calendar.getInstance().getTime());

		final Note result = this.noteRepository.save(res);
		return result;
	}
	//35.3
	public Note addCommentCustomer(final Note note, final String comment) {
		Assert.notNull(note);
		Assert.notNull(note.getId());

		//Logged user must be a customer
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.CUSTOMER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//Note's owner is logged customer
		Assert.isTrue(this.customerService.findByPrincipal() == note.getCustomer());
		note.setCustomerComment(comment);
		this.noteRepository.save(note);
		return note;
	}

	public Note addCommentReferee(final Note note, final String comment) {
		Assert.notNull(note);
		Assert.notNull(note.getId());

		//Logged user must be a referee
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.REFEREE);
		Assert.isTrue(user.getAuthorities().contains(a));

		//Note's owner is logged customer
		Assert.isTrue(this.refereeService.findByPrincipal() == note.getReferee());
		note.setRefereeComment(comment);
		this.noteRepository.save(note);
		return note;
	}

	public Note addCommentHandyWorker(final Note note, final String comment) {
		Assert.notNull(note);
		Assert.notNull(note.getId());

		//Logged user must be a referee
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//Note's owner is logged customer
		Assert.isTrue(this.handyWorkerService.findByPrincipal() == note.getHandyWorker());
		note.setHandyWorkerComment(comment);
		this.noteRepository.save(note);
		return note;
	}

	public Collection<Note> findAll() {
		return this.noteRepository.findAll();
	}
}
