
package services;

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
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class MessageServiceTest extends AbstractTest {

	//Service
	@Autowired
	private MessageService	messageService;


	//Test
	@Test
	public void testSave() {
		System.out.println("------Test Save------");
		final Message mes, saved;
		System.out.println("algo");
		mes = this.messageService.create();
		System.out.println("algo2");
		super.authenticate("customer");
		System.out.println("algo3");

		try {
			System.out.println("0");
			mes.setMoment(new Date());
			mes.setPriority("HIGH");
			mes.setTag("tag1");
			mes.setBody("body1");
			mes.setSubject("subject1");
			mes.setFlagSpam(false);
			mes.setSender(new Customer());
			mes.setRecipient(new HandyWorker());
			System.out.println("1");
			saved = this.messageService.save(mes);
			System.out.println("3");
			Assert.isTrue(this.messageService.findAll().contains(saved));
			System.out.println("2");
			//			this.messageService.delete(mes);
			//			Assert.isNull(this.messageService.findOne(mes));

			super.unauthenticate();
			//super.authenticate("admin");
			//			final Message mes2 = this.messageService.createForActor(new HandyWorker());
			//			Assert.notNull(mes2);

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

}
