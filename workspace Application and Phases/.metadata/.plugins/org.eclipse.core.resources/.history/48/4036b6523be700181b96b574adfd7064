package domain;

import java.time.LocalDate;
import java.util.Collection;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class FixUpTask extends DomainEntity {
	
	private String	ticker;
	private Date	moment;
	private String	description;
	private String	adress;
	private Money	maximumPrice;
	private Date	startDate;
	private Date	endDate;
	
	@Column(unique=true)
	@Pattern(regexp = "^([0][0-9]|[1][0-9])(0[0-9]|1[0-2])(0[0-9]|[12][0-9]|3[01])-[A-Z0-9_]{6}$")
	public String getTicker() {
		return ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public LocalDate getMoment() {
		return moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	@NotBlank
	public String getAdress() {
		return adress;
	}
	public void setAdress(final String adress) {
		this.adress = adress;
	}
	@AttributeOverrides({
		@AttributeOverride(name="amount",
			column=@Column(name="maximumAmount")),
		@AttributeOverride(name="currency",
			column=@Column(name="maximumCurrency"))
	})	
	public Money getMaximumPrice() {
		return maximumPrice;
	}
	public void setMaximumPrice(final Money maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	@NotBlank
	@Temporal(TemporalType.DATE)
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	@NotBlank
	@Temporal(TemporalType.DATE)
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	//Relationships
	private Category category;
	private Collection<Warranty> warranty;
	private Collection<Phase> phases;
	private Customer customer;
	private Complaint complaint;

	@ManyToOne(optional=false)
	public Category getCategory() {
		return category;
	}
	public void setCategory(final Category category) {
		this.category = category;
	}
	@OneToMany
	public Collection<Warranty> getWarranty() {
		return warranty;
	}
	public void setWarranty(final Collection<Warranty> warranty) {
		this.warranty = warranty;
		
	@OneToMany
	public Collection<Phase> getPhases() {
		return phases;
	}
	public void setPhases(final Collection<Phase> phases) {
		this.phase = phase;
	}
	public Customer getCustomer(){
		return customer;
	}
	@ManyToOne(optional=false)
	public void setCustomer(final Customer customer){
		this.customer=customer;
	}
	@OneToOne(optional=true)
	public Complaint getComplaint(){
		return complaint;
	}
	public Complaint setComplaint(final Complaint complaint){
		this.complaint()=complaint;
	}
}