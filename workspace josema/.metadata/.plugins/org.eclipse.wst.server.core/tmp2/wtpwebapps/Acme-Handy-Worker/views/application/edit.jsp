<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('CUSTOMER')|| hasRole('HANDYWORKER')">
<form:form action="application/handyWorker/edit.do" modelAttribute="application">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	
	<security:authorize access="hasRole('HANDYWORKER')">
	
	<form:hidden path="rejectedCause" />
	<form:hidden path="creditCard" />
	<form:hidden path="status"/>
	

	<!-- Parámetros -->

	
	<form:label path="comment">
		<spring:message code="application.comment" />:
	</form:label>
	<form:input path="comment" />
	<form:errors cssClass="error" path="comment" />
	<br />
	
	
	
	<form:label path="offeredPrice.currency">
		<spring:message code="application.offeredPrice.currency" />:
	</form:label>
	<form:input path="offeredPrice.currency" />
	<form:errors cssClass="error" path="offeredPrice.currency" />
	<br />
	</security:authorize>
	<!--<form:label path="offeredPrice.amount">
		<spring:message code="application.offeredPrice.amount" />:
	</form:label>
	<form:input path="offeredPrice.amount" />
	<form:errors cssClass="error" path="offeredPrice" />-->
	<br />
	
	
	
	<security:authorize access="hasRole('CUSTOMER')">
	
	<form:label path="status">
	<spring:message code="application.status" />:
	</form:label>
	<form:input path="status" />
	<form:errors cssClass="error" path="status" />
	<br />
	
	<%@page import = "java.util.List" %>
	<%@page import = "java.util.ArrayList" %>
	<%
	List<String> statuss = new ArrayList<String>();
	statuss.add("PENDING");
	statuss.add("ACCEPTED");
	statuss.add("REJECTED");
	%>
	<form:select path="status" id="statusDropdown" >	
	 	<form:option value="PENDING">PENDING</form:option>
		<form:option value="ACCEPTED">ACCEPTED</form:option>
		<form:option value="REJECTED">REJECTED</form:option>
		
	</form:select>
	
	
	<script type="text/javascript">
	function leaveChange() {
		var statusValue = document.getElementById("statusDropdown").value;
		var rejectedCauseContainer = document.getElementById("rejectedCauseContainer");
	    if (document.getElementById("statusDropdown").value == "REJECTED"){
            var rejectedInput = document.createElement("rejectedInput");
            rejectedInput.type = "text";
            rejectedInput.name = "rejectedCause";
            rejectedInput.id = "rejectedCause";
            rejectedInput.label = "Rejected cause";
            rejectedCauseContainer.appendChild(rejectedInput);
	    }     
	    else if(document.getElementById("statusDropdown").value == "ACCEPTED"){
            var ccHolderInput = document.createElement("ccHolderInput");
            ccHolderInput.type = "text";
            ccHolderInput.name = "ccHolder";
            ccHolderInput.id = "ccHolder";
            ccHolderInput.label = "Holder";
            creditCardContainer.appendChild(ccHolderInput);
            
            var ccBrandInput = document.createElement("ccBrandInput");
            ccBrandInput.type = "text";
            ccBrandInput.name = "ccBrand";
            ccBrandInput.id = "ccBrand";
            ccBrandInput.label = "Brand";
            creditCardContainer.appendChild(ccBrandInput);
            
            var ccNumberInput = document.createElement("ccNumberInput");
            ccNumberInput.type = "text";
            ccNumberInput.name = "ccNumber";
            ccNumberInput.id = "ccNumber";
            ccNumberInput.label = "Number";
            creditCardContainer.appendChild(ccNumberInput);
            
            var ccMonthInput = document.createElement("ccMonthInput");
            ccMonthInput.type = "text";
            ccMonthInput.name = "ccMonth";
            ccMonthInput.id = "ccMonth";
            ccMonthInput.label = "Expiration month";
            creditCardContainer.appendChild(ccMonthInput);
            
            var ccYearInput = document.createElement("ccYearInput");
            ccYearInput.type = "text";
            ccYearInput.name = "ccYear";
            ccYearInput.id = "ccYear";
            ccYearInput.label = "Expiration year";
            creditCardContainer.appendChild(ccYearInput);
            
            var ccCVVInput = document.createElement("ccHolderInput");
            ccCVVInput.type = "text";
            ccCVVInput.name = "ccCVV";
            ccCVVInput.id = "ccCVV";
            ccCVVInput.label = "CVV";
            creditCardContainer.appendChild(ccCVVInput);
            
            
	    }        
	}
	</script>
	
	<input type="text" name="" />
	<fieldset>
	<legend align="left"><spring:message code="application.edit.customer" /></legend>
	
	<div id="rejectedCauseContainer">
	</div>
	
	<div id="creditCardContainer">
	</div>

	</security:authorize>
	

	<input type="submit" name="save"
		value="<spring:message code="application.save" />" />&nbsp; 
		
	
	<input type="button" name="cancel"
		value="<spring:message code="application.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/list.do');" />
	<br />


</form:form>
</security:authorize>