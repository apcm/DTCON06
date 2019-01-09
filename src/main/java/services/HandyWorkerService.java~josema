
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Box;
import domain.Category;
import domain.Customisation;
import domain.Endorsement;
import domain.Finder;
import domain.HandyWorker;
import domain.Phase;
import domain.SocialProfile;
import domain.Warranty;

@Service
@Transactional
public class HandyWorkerService {

	//Repository
	@Autowired
	public HandyWorkerRepository	handyWorkerRepository;

	//Services
	@Autowired
	public AdministratorService		administratorService;

	@Autowired
	public FinderService			finderService;

	@Autowired
	public CategoryService			categoryService;

	@Autowired
	public WarrantyService			warrantyService;


	//Constructor
	public HandyWorkerService() {
		super();
	}

	//Simple CRUD

	//8.1
	public HandyWorker create() {

		HandyWorker result;
		result = new HandyWorker();

		//Actor
		final Box trash = new Box();
		final Box out = new Box();
		final Box spam = new Box();
		final Box in = new Box();
		trash.setName("trash");
		in.setName("in");
		out.setName("out");
		spam.setName("spam");
		out.setPredefined(true);
		in.setPredefined(true);
		spam.setPredefined(true);
		trash.setPredefined(true);
		final List<Box> predefined = new ArrayList<Box>();
		//		predefined.add(in);
		//		predefined.add(out);
		//		predefined.add(spam);
		//		predefined.add(trash);

		final UserAccount newUser = new UserAccount();
		final Authority f = new Authority();
		f.setAuthority(Authority.HANDYWORKER);
		final List<Application> applications = new ArrayList<Application>();
		final List<Phase> phases = new ArrayList<Phase>();
		newUser.addAuthority(f);

		result.setBoxes(new ArrayList<Box>(predefined));
		result.setSocialProfiles(new ArrayList<SocialProfile>());
		result.setName("");
		result.setEmail("");
		result.setAddress("");
		result.setSurname("");
		result.setPhoneNumber("");
		result.setPhotoURL("");
		result.setApplications(applications);

		//HandyWorker
		result.setScore(0);

		result.setUserAccount(newUser);
		//TODO: Make name
		result.setMake("");
		result.setPlannedPhases(phases);
		final Finder find = this.finderService.create();
		result.setFinder(new Finder());
		return result;
	}
	//9.2
	public HandyWorker save(final HandyWorker handyWorker) {
		Assert.notNull(handyWorker);
		Assert.isTrue(!handyWorker.getBan());

		//Logged user must be a HandyWorker
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.HANDYWORKER);
		Assert.isTrue(user.getAuthorities().contains(a));

		//Modified handyWorker must be logged handyWorker
		final HandyWorker logHandyWorker;
		logHandyWorker = this.findByPrincipal();
		Assert.notNull(logHandyWorker);
		Assert.notNull(logHandyWorker.getId());

		HandyWorker res;
		String make;

		if (handyWorker.getMake() == null || handyWorker.getMake().equals("")) {
			make = handyWorker.getName() + " " + handyWorker.getSurname();
			handyWorker.setMake(make);
		}

		res = this.handyWorkerRepository.save(handyWorker);
		return res;
	}

	//Complex methods

	//Returns logged handyWorker
	public HandyWorker findByPrincipal() {
		HandyWorker res;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.findByUserAccount(userAccount);
		Assert.notNull(res);

		return res;
	}

	//Returns logged customer from his or her userAccount
	public HandyWorker findByUserAccount(final UserAccount userAccount) {
		HandyWorker res;
		Assert.notNull(userAccount);
		res = this.handyWorkerRepository.findByUserAccountId(userAccount.getId());

		return res;
	}

	//12.5
	public Collection<HandyWorker> handyWorkersWithMoreApplications() {
		//Logged user must be an admin
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		return this.handyWorkerRepository.handyWorkersWithMoreApplications();
	}

	//38.5
	public Collection<HandyWorker> topThreeHandyWorkersByComplaints() {
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));

		return this.handyWorkerRepository.topThreeHandyWorkersByComplaints();
	}

	public Collection<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	public HandyWorker saveScore(final HandyWorker handyWorker, final Customisation custo) {
		//Logged user must be an administrator
		final Authority a = new Authority();
		final UserAccount user = LoginService.getPrincipal();
		a.setAuthority(Authority.ADMIN);
		Assert.isTrue(user.getAuthorities().contains(a));
		Assert.notNull(handyWorker);
		Assert.notNull(custo);
		Integer score = 0;

		final List<String> positive = new ArrayList<String>(custo.getPositiveWords());
		final List<String> negative = new ArrayList<String>(custo.getNegativeWords());
		final List<Endorsement> endorsements = new ArrayList<Endorsement>(handyWorker.getEndorsements());

		for (final Endorsement e : endorsements) {
			final String text = e.getComment();
			for (final String p : positive)
				if (text.contains(p))
					score++;
			for (final String n : negative)
				if (text.contains(n))
					score--;
		}
		handyWorker.setScore(score);
		return this.handyWorkerRepository.save(handyWorker);
	}

	public HandyWorker saveForTest(final HandyWorker hw) {

		Assert.isTrue(hw.getBan() != true);
		String make = "";

		if (hw.getMake().equals("")) {
			make = hw.getName() + " " + hw.getSurname();
			hw.setMake(make);
		}

		if (hw.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String oldpass = hw.getUserAccount().getPassword();
			final String hash = encoder.encodePassword(oldpass, null);

			final UserAccount cuenta = hw.getUserAccount();
			cuenta.setPassword(hash);
			hw.setUserAccount(cuenta);

			final Finder f = new Finder();
			f.setKeyWord("");
			final Category cat = this.categoryService.findOne(2631);
			f.setCategory(cat);
			final Warranty war = this.warrantyService.findOne(2600);
			f.setWarranty(war);
			final Finder find = this.finderService.saveForTest(f);
			hw.setFinder(find);
		}

		return this.handyWorkerRepository.save(hw);
	}
}
