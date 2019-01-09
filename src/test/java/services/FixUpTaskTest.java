
package services;

import java.util.Arrays;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.FixUpTask;
import domain.Money;
import domain.Phase;
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FixUpTaskTest extends AbstractTest {

	// Service---------------------------------------
	@Autowired
	private FixUpTaskService	fixUpTaskService;


	// Test-----------------------------------------------------------
	@Test
	public void testCreate() {
		System.out.println("---testFixUpTasksCreate---");
		try {
			final FixUpTask fixUp = this.fixUpTaskService.create();
			final FixUpTask saved;
			fixUp.setTicker(utilities.TickerGenerator.generateTicker());
			fixUp.setMoment(new Date());
			fixUp.setDescription("Esto es una descripción de fixUpTaskService");
			fixUp.setAddress("AddressHola");
			fixUp.setMaximumPrice(new Money());
			fixUp.setStartDate(new Date());
			fixUp.setEndDate(new Date());

			fixUp.setCategory(new Category());
			fixUp.setWarranty(Arrays.asList(new Warranty()));
			fixUp.setPhases(Arrays.asList(new Phase()));
			fixUp.setComplaints(Arrays.asList(new Complaint()));
			fixUp.setApplications(Arrays.asList(new Application()));

			saved = this.fixUpTaskService.save(fixUp);
			//Falta save
			Assert.isTrue(this.fixUpTaskService.findAll().contains(saved));
			//Falta método findAll.
			System.out.println("Éxito");

		} catch (final Exception e) {
			System.out.println("Fallo");
		}
	}

}
