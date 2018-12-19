
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ReportServiceTest extends AbstractTest {

	//Service----------------------------------------------------------
	@Autowired
	private ReportService		reportService;

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private RefereeService		refereeService;


	//Test-------------------------------------------------------------

	@Test
	public void testReport() {
		System.out.println("------Test Report------");
		final Report report, saved;
		final Collection<Report> reports;
		super.authenticate("referee1");
		report = this.reportService.create();
		try {
			//			report = this.reportService.create();
			//			report.setMoment(new Date());
			//			report.setDescription("This is a description.");
			//			report.setAttachments("http://www.attachments1.com");
			//			report.setNotes(new ArrayList<Note>());
			//
			//			final ArrayList<Complaint> comps = new ArrayList<>();
			//			comps.addAll(this.complaintService.findAll());
			//			final Complaint comp = comps.get(0);
			//			report.setComplaint(comp);
			//
			//			saved = this.reportService.save(report);
			//
			//			final ArrayList<Referee> refs = new ArrayList<>();
			//			refs.addAll(this.refereeService.findAll());
			//			final Referee ref = refs.get(0);
			//			final ArrayList<Report> rs = new ArrayList<>();
			//			rs.addAll(ref.getReports());
			//			rs.add(report);
			//			ref.setReports(rs);
			//			final Referee savedRef = this.refereeService.save(ref);
			//
			//			Assert.notNull(saved);
			//			reports = this.reportService.findAll();
			//
			//			Assert.isTrue(reports.contains(saved));

			final ArrayList<Report> reportsForSave = new ArrayList<>();
			reportsForSave.addAll(this.reportService.findAll());
			final Report rForSave = reportsForSave.get(0);
			System.out.println("Old description: " + rForSave.getDescription());
			rForSave.setDescription("new descrip");
			final Report rSaved = this.reportService.save(rForSave);
			System.out.println("New Description: " + rSaved.getDescription());

			System.out.println("Éxito");
		} catch (final Exception e) {
			System.out.println("¡Fallo, " + e.getMessage() + "!");
		}
	}
}
