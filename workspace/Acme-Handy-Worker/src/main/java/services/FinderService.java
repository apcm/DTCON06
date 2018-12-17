
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;

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
		//Logged Account must be a HandyWorker
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		final Finder f;
		f = this.finderRepository.finderById(id);
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
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		final Finder fd = new Finder();
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
			System.out.println("caso 1");
			System.out.println(finder.getKeyWord());
			results.addAll(this.fixUpTaskService.fixUpTaskFilterByKeyword(finder.getKeyWord()));
		}
		if (finder.getCategory() != null || finder.getCategory() != "") {
			System.out.println("caso 2");
			results.addAll(this.fixUpTaskService.fixUpTaskFilterByCategory(finder.getCategory()));
		}
		if (finder.getWarranty().getId() != 0 || finder.getWarranty().getId() != 0) {
			System.out.println("caso 3");
			results.addAll(this.fixUpTaskService.fixUpTaskFilterByWarranty(finder.getWarranty().getId()));
		}
		if (finder.getStartDate() != null && finder.getEndDate() != null) {
			System.out.println("caso 4");
			results.addAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfDates(finder.getStartDate(), finder.getEndDate()));
		}
		if (finder.getMinPrice() != null && finder.getMaxPrice() != null) {
			System.out.println("caso 5");
			results.addAll(this.fixUpTaskService.fixUpTaskFilterByRangeOfPrices(finder.getMinPrice().getAmount(), finder.getMaxPrice().getAmount()));
		}
		finder.setFixUpTasks(results);
		System.out.println("antes de save ok");
		f = this.save(finder);
		return f;
	}

	public Collection<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder saveForTest(final Finder finder) {
		return this.finderRepository.save(finder);
	}
}
