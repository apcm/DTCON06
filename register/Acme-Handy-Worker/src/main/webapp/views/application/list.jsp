<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMINISTRATOR')">

	<h1>
		<spring:message code="application.list" />
	</h1>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="applications" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	
	<display:column property="moment" titleKey="application.moment" sortable="true" />

	<spring:message code="application.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" sortable="false" />

	<spring:message code="application.offeredPrice" var="offeredPriceHeader" />
	<display:column property="offeredPrice" title="${offeredPriceHeader}" sortable="false" />

	<spring:message code="application.comment" var="commentHeader" />
	<display:column property="comment" title="${commentHeader}"	sortable="false" />
	
	<spring:message code="application.rejectedCause" var="rejectedCauseHeader" />
	<display:column property="rejectedCause" title="${rejectedCauseHeader}"	sortable="false" />
	
	
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
					<a href="application/customer/edit.do?applicationId=${row.id}">
						<spring:message code="application.edit" />
					</a>					
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('HANDYWORKER')">
		<display:column>
					<a href="application/handyworker/show.do?applicationId=${row.id}">
						<spring:message code="application.show" />
					</a>					
		</display:column>		
	</security:authorize>
</display:table>
</security:authorize>
<!-- 
<security:authorize access="hasRole('HANDYWORKER')">
	<div>
		<a href="application/handyworker/create.do"> <spring:message
				code="application.create" />
		</a>
	</div>
</security:authorize>

 -->