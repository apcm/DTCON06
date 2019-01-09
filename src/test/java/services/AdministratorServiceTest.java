
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
import domain.Administrator;
import domain.Box;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	//Service
	@Autowired
	private AdministratorService	administratorService;


	//Test
	@Test
	public void testCreate() {
		System.out.println("------Test Create------");
		super.authenticate("admin");
		final Administrator admin, saved;
		final Collection<SocialProfile> sp1 = new ArrayList<>();
		final Collection<Box> boxes1 = new ArrayList<>();
		admin = this.administratorService.create();
		try {

			admin.setName("Pepe");
			admin.setEmail("actorPepe@gmail.com");
			admin.setPhoneNumber("123456789");
			admin.setAddress("PepeAddress");
			admin.setBan(false);
			admin.setMiddleName("PepeMiddleName");
			admin.setSurname("PepeSurname");
			admin.setPhotoURL("http://www.urlpepe.com");
			admin.setSocialProfiles(sp1);
			admin.setBoxes(boxes1);
			saved = this.administratorService.save(admin);
			Assert.isTrue(this.administratorService.findAll().contains(saved));

			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

}
