
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Money;

@Service
@Transactional
public class FinderService {

	//Repositories
	@Autowired
	public FinderRepository		finderRepository;

	//Services
	@Autowired
	public HandyWorkerService	handyWorkerService;

	@Autowired
	public FixUpTaskService		fixUpTaskService;


	public Finder finderById(final Integer id) {
		/*
		 * //Logged Account must be a HandyWorker
		 * final Authority a = new Authority();
		 * final UserAccount user = LoginService.getPrincipal();
		 * a.setAuthority(Authority.HANDYWORKER);
		 * Assert.isTrue(user.getAuthorities().contains(a));
		 */

		final Finder f;
		f = this.finderRepository.finderById(id);
		return f;
	}

	public Finder findOne(final Integer finderId) {
		/*
		 * //Logged Account must be a HandyWorker
		 * final Authority a = new Authority();
		 * final UserAccount user = LoginService.getPrincipal();
		 * a.setAuthority(Authority.HANDYWORKER);
		 * Assert.isTrue(user.getAuthorities().contains(a));
		 */
		final Finder f;
		f = this.finderRepository.findOne(finderId);
		return f;
	}

	//Constructor
	public FinderService() {
		super();
	}

	//methods
	public Finder create() {
		//Logged Account must be a HandyWorker
		//		Assert.isTrue(!LoginService.getPrincipal().isAccountNonLocked());
		//		final Authority a = new Authority();
		//		final UserAccount user = LoginService.getPrincipal();
		//		a.setAuthority(Authority.HANDYWORKER);
		//Assert.isTrue(user.getAuthorities().contains(a));

		final Category cat = new Category();
		cat.setName("CATEGORY");
		final Finder fd = new Finder();
		fd.setMoment(Calendar.getInstance().getTime());
		fd.setCategory(cat);
		final Collection<FixUpTask> fut = new ArrayList<FixUpTask>();
		fd.setFixUpTasks(fut);
		fd.setMinPrice(new Money());
		fd.setMaxPrice(new Money());
		return fd;
	}

	//37.1
	public Finder save(final Finder finder) {
		Assert.notNull(finder);

		//Logged Account must be a HandyWorker
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//finder owner's ID must be the same as Logged HandyWorker's ID
		final HandyWorker logHandyWorker;
		logHandyWorker = this.handyWorkerService.findByPrincipal();
		Assert.notNull(logHandyWorker);
		Assert.isTrue(logHandyWorker.getFinder().equals(finder));

		final Finder f;

		f = this.finderRepository.save(finder);
		return f;
	}

	//Save para ejercicio
	public Finder saveResults(final Finder finder) {
		Assert.notNull(finder);

		//Logged Account must be a HandyWorker
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//finder owner's ID must be the same as Logged HandyWorker's ID
		final HandyWorker logHandyWorker;
		logHandyWorker = this.handyWorkerService.findByPrincipal();
		Assert.notNull(logHandyWorker);
		Assert.isTrue(logHandyWorker.getFinder().equals(finder));

		final Finder f;
		final FixUpTask f1 = new FixUpTask();
		final FixUpTask f2 = new FixUpTask();
		final Collection<FixUpTask> fut = new ArrayList<FixUpTask>();
		fut.add(f1);
		fut.add(f2);
		finder.setFixUpTasks(fut);
		f = this.finderRepository.save(finder);
		return f;
	}
	//37.2

	public Finder saveResultsFixUpTasks(final Finder finder) {
		Assert.notNull(finder);

		//Logged Account must be a HandyWorker
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//finder owner's ID must be the same as Logged HandyWorker's ID
		final HandyWorker logHandyWorker;
		logHandyWorker = this.handyWorkerService.findByPrincipal();
		Assert.notNull(logHandyWorker);
		Assert.isTrue(logHandyWorker.getFinder().equals(finder));

		final Finder f;
		final Collection<FixUpTask> results = new ArrayList<FixUpTask>();
		if (finder.getKeyWord() != null || finder.getKeyWord() != "") {
			System.out.println(finder.getKeyWord());
			results.addAll(this.fixUpTaskService.fixUpTaskFilterByKeyword(finder.getKeyWord()));
		}
		if (finder.getCategory() != null || finder.getCategory().getName() != "")
			results.retainAll(this.fixUpTaskService.fixUpTaskFilterByCategory(finder.getCategory().getName()));
		//if (finder.getWarranty() != null || finder.getWarranty().getTitle() != "")
		//	results.retainAll(this.fixUpTaskService.fixUpTaskFilterByWarranty(finder.getWarranty().getTitle()));
		//if (finder.getStartDate() != null && finder.getEndDate() != null)
		//	results.retainAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfDates(finder.getStartDate(), finder.getEndDate()));
		//if (finder.getMinPrice() != null && finder.getMaxPrice() != null) {
		//	results.retainAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfPrices(finder.getMinPrice().getAmount(), finder.getMaxPrice().getAmount()));
		//}
		finder.setFixUpTasks(results);
		f = this.save(finder);
		return f;
	}
	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder saveForTest(final Finder finder) {
		return this.finderRepository.save(finder);
	}

	public Finder findByHWId(final int handyWorkerId) {
		return this.finderRepository.finderByHwId(handyWorkerId);
	}
}
