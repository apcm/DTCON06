
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
import domain.Actor;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ActorServiceTest extends AbstractTest {

	//Service
	@Autowired
	private ActorService	actorService;

	@Autowired
	private CustomerService	customerService;


	//Test
	@Test
	public void testBanActor() {
		System.out.println("------Test BanActor------");
		super.authenticate("admin");
		final ArrayList<Customer> actors = new ArrayList<>();
		System.out.println(actors);
		actors.addAll(this.customerService.findAll());
		System.out.println(actors);
		final Customer actor = actors.get(1);
		System.out.println(actor);
		actor.setBan(false);
		System.out.println(actor);
		try {
			final Actor actor2 = this.actorService.banActor(actor);
			Assert.isTrue(actor2.getBan() == true);

			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	@Test
	public void testUnbanActor() {
		System.out.println("------Test UnbanActor------");
		super.authenticate("admin");
		final ArrayList<Customer> actors = new ArrayList<>();
		System.out.println(actors);
		actors.addAll(this.customerService.findAll());
		System.out.println(actors);
		final Customer actor = actors.get(1);
		System.out.println(actor);
		actor.setBan(true);
		System.out.println(actor);

		try {
			final Actor actor2 = this.actorService.unbanActor(actor);
			Assert.isTrue(actor2.getBan() == false);

			System.out.println("Success!");
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
		super.unauthenticate();
	}

	@Test
	public void testSuspiciousActors() {
		super.authenticate("admin");
		try {
			final Collection<Actor> sus = this.actorService.suspiciousActors();
			Assert.notNull(sus);
			System.out.println("Success!");
			super.unauthenticate();
		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
}
