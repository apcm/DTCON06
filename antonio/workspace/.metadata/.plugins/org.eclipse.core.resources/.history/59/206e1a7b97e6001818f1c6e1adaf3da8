
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Endorser {

	private double					score;

	private Collection<FixUpTask>	fixuptasks;
	private Collection<Note>		notes;
	private Collection<Complaint>	complaints;


	@Digits(integer = 1, fraction = 2)
	@Min(0)
	public double getScore() {
		return this.score;
	}

	public void setScore(final double score) {
		this.score = score;
	}

	@OneToMany
	public Collection<FixUpTask> getFixuptasks() {
		return this.fixuptasks;
	}

	public void setFixuptasks(final Collection<FixUpTask> fixuptasks) {
		this.fixuptasks = fixuptasks;
	}

	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	@OneToMany
	public Collection<Complaint> getComplaint() {
		return this.complaints;
	}

	public void setComplaint(final Collection<Complaint> complaint) {
		this.complaints = complaint;
	}

}
