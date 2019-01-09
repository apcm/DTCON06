
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
import domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CategoryServiceTest extends AbstractTest {

	//Service
	@Autowired
	private CategoryService	categoryService;


	//Test
	@Test
	public void testCategory() {
		System.out.println("------Test Category------");
		super.authenticate("admin");
		final Category cat, saved;
		cat = this.categoryService.create();
		try {
			cat.setName("Category1");
			final ArrayList<Category> cats = new ArrayList<>();
			cats.addAll(this.categoryService.findAll());
			final Category cat2 = cats.get(1);
			cat.setParentCategory(cat2);
			saved = this.categoryService.save(cat);
			Assert.isTrue(this.categoryService.findAll().contains(saved));

			this.categoryService.delete(saved);
			Assert.isNull(this.categoryService.findOne(saved));

			super.unauthenticate();

			System.out.println("Success!");

		} catch (final Exception e) {
			System.out.println("Error, " + e.getMessage() + "!");
		}
	}

}
