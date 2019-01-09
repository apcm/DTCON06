
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Note extends Report {

	private Date	momentNote;
	private String	mandatoryComment;
	private String	refereeComment;
	private String	handWorkerComment;


	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getmomentNote() {
		return this.momentNote;
	}

	public void setmomentNote(final Date momentNote) {
		this.momentNote = momentNote;
	}

	@NotBlank
	public String getMandatoryComment() {
		return this.mandatoryComment;
	}
	public void setMandatoryComment(final String mandatoryComment) {
		this.mandatoryComment = mandatoryComment;
	}
	@NotBlank
	public String getRefereeComment() {
		return this.refereeComment;
	}
	public void setRefereeComment(final String refereeComment) {
		this.refereeComment = refereeComment;
	}

	@NotBlank
	public String getHandWorkerComment() {
		return this.handWorkerComment;
	}

	public void setHandWorkerComment(final String handWorkerComment) {
		this.handWorkerComment = handWorkerComment;
	}
}
