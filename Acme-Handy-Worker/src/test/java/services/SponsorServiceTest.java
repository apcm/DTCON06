
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
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SponsorServiceTest extends AbstractTest {

	//Service
	@Autowired
	private SponsorService	sponsorService;


	//Test 47.1
	@Test
	public void testCreate() {
		System.out.println("------Test CreateSponsor------");
		//		final Sponsor spo, saved;
		final Sponsor saved;
		//		final Collection<SocialProfile> sp1 = new ArrayList<>();
		//		final Collection<Box> boxes1 = new ArrayList<>();
		//		final Collection<Sponsorship> ss1 = new ArrayList<>();
		//spo = this.sponsorService.create();
		try {
			//Actor
			//			System.out.println("user " + spo.getUserAccount());
			//			spo.setName("Pedro");
			//			spo.setEmail("pedrogongust@gmail.com");
			//			spo.setPhoneNumber("653983457");
			//			spo.setAddress("C/Buenavida");
			//			//spo.setBan(false);
			//			spo.setMiddleName("Gonzalez");
			//			spo.setSurname("Gustavo");
			//			spo.setPhotoURL("http://www.urlpepe.com");
			//			spo.setSocialProfiles(sp1);
			//			spo.setBoxes(boxes1);
			//			//Sponsor
			//			spo.setSponsorships(ss1);
			System.out.println("1");
			final ArrayList<Sponsor> spr = new ArrayList<>();
			System.out.println("C");
			System.out.println("findall: " + this.sponsorService.findAll());
			spr.addAll(this.sponsorService.findAll());
			System.out.println("B");
			final Sponsor uno = spr.get(0);
			System.out.println("A");
			saved = this.sponsorService.save(uno);
			System.out.println("2");

			Assert.isTrue(this.sponsorService.findAll().contains(saved));
			System.out.println("3");

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}
}
