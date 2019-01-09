
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
import domain.Endorsement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorsementServiceTest extends AbstractTest {

	//Service
	@Autowired
	private EndorsementService	endorsementService;

	@Autowired
	private CustomerService		customerService;


	//Test
	@Test
	public void testEndorsement() {
		System.out.println("------Test Endorsement------");
		super.authenticate("customer");
		final Endorsement end, saved;
		end = this.endorsementService.create();
		try {
			super.authenticate("customer");
			end.setComment("comment1");
			end.setMoment(new Date());
			final ArrayList<Customer> custs = new ArrayList<>();
			custs.addAll(this.customerService.findAll());
			final Customer cust = custs.get(1);
			final Customer cust2 = custs.get(2);
			end.setSender(cust);
			end.setRecipient(cust2);
			saved = this.endorsementService.save(end);
			Assert.isTrue(this.endorsementService.findAll().contains(saved));

			//findByCustomer
			System.out.println("Test findByCustomer");
			final Collection<Endorsement> endsCust = this.endorsementService.findByCustomer();
			System.out.println(endsCust);
			Assert.notNull(endsCust);

			//saveByCustomer
			System.out.println("Test saveByCustomer");
			final ArrayList<Endorsement> sbc = new ArrayList<>();
			sbc.addAll(endsCust);
			final Endorsement endCust = sbc.get(1);
			System.out.println(endCust.getComment());
			endCust.setComment("ejemplo");
			final Endorsement endSavedByCust = this.endorsementService.saveByCustomer(endCust);
			System.out.println("Endorsement saved by customer");
			Assert.notNull(endSavedByCust);
			final Collection<Endorsement> endsCust2 = this.endorsementService.findByCustomer();
			final ArrayList<Endorsement> sbc2 = new ArrayList<>();
			sbc2.addAll(endsCust2);
			final Endorsement endCust2 = sbc2.get(1);
			System.out.println(endCust2.getComment());

			//deleteByCustomer
			final ArrayList<Endorsement> copiaEndsCust = new ArrayList<Endorsement>();
			copiaEndsCust.addAll(this.endorsementService.findByCustomer());
			System.out.println("Lista ends: " + copiaEndsCust);
			final Endorsement endCustDel = copiaEndsCust.get(1);
			System.out.println("Endorsement para delete: " + endCustDel);
			this.endorsementService.deleteByCustomer(endCustDel);
			System.out.println("Endorsement deleted by customer");
			final Collection<Endorsement> endsTotales = this.endorsementService.findAll();
			System.out.println("Todas las ends: " + endsTotales);
			final Collection<Endorsement> deleteList = this.endorsementService.findByCustomer();
			System.out.println("DeleteList: " + deleteList);
			Assert.isTrue(!deleteList.contains(endCustDel));

			System.out.println("DeleteByCustomer working");

			super.unauthenticate();
			super.authenticate("handyWorker");
			//findByHandyWorker
			System.out.println("Test findByHandyWorker");
			final Collection<Endorsement> endsHw = this.endorsementService.findByHandyWorker();
			System.out.println(endsHw);
			Assert.notNull(endsHw);

			//saveByHandyWorker
			System.out.println("Test saveByHandyWorker");
			final ArrayList<Endorsement> sbh = new ArrayList<>();
			sbh.addAll(endsHw);
			final Endorsement endHw = sbh.get(1);
			System.out.println(endHw.getComment());
			endHw.setComment("ejemplo");
			final Endorsement endSavedByHw = this.endorsementService.saveByHandyWorker(endHw);
			System.out.println("Endorsement saved by handyWorker");
			Assert.notNull(endSavedByHw);
			final Collection<Endorsement> endsHw2 = this.endorsementService.findByHandyWorker();
			final ArrayList<Endorsement> sbh2 = new ArrayList<>();
			sbh2.addAll(endsHw2);
			final Endorsement endHw2 = sbh2.get(1);
			System.out.println(endHw2.getComment());

			//deleteByHandyWorker
			final ArrayList<Endorsement> copiaEndsHw = new ArrayList<Endorsement>();
			copiaEndsHw.addAll(endsHw2);
			final Endorsement endHwDel = copiaEndsHw.get(1);
			System.out.println("Endorsement para delete: " + endHwDel);
			this.endorsementService.deleteByHandyWorker(endHwDel);
			System.out.println("Endorsement deleted by HandyWorker");
			final Collection<Endorsement> deleteListHw = this.endorsementService.findByHandyWorker();
			System.out.println("DeleteListHw: " + deleteListHw);
			Assert.isTrue(!deleteListHw.contains(endHwDel));

			System.out.println("DeleteByHandyWorker working");

			super.unauthenticate();
			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
}
