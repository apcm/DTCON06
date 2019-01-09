<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="fixUpTask.show.head"/>	
</h1>
<br/>
<spring:message code="fixUpTask.show.ticker"/>
<jstl:out value="${fixUpTask.ticker}"/>
<br/>
<spring:message code="fixUpTask.show.moment"/>
<jstl:out value="${fixUpTask.moment}"/>
<br/>
<spring:message code="fixUpTask.show.description"/>
<jstl:out value="${fixUpTask.description}"/>
<br/>
<spring:message code="fixUpTask.show.address"/>
<jstl:out value="${fixUpTask.address}"/>
<br/>
<spring:message code="fixUpTask.show.maxPrice"/>
<jstl:out value="${fixUpTask.maxPrice}"/>
<br/>
<spring:message code="fixUpTask.show.startDate"/>
<jstl:out value="${fixUpTask.startDate}"/>
<br/>
<spring:message code="fixUpTask.show.endDate"/>
<jstl:out value="${fixUpTask.endDate}"/>
<br/>

<h2>
	<spring:message code="fixUpTask.show.warranties"/>
</h2>
<br/>
<display:table class="displaytag" keepStatus="true"
	name="warranties" list="${fixUpTask.warranties}" id="row">
<!-- Attributes -->
<spring:message code="fixUpTask.show.warranties.title" var="title"/>
<display:column property="title" title="${title}"/>
<br/>
<spring:message code="fixUpTask.show.warranties.terms" var="terms"/>
<display:column property="terms" title="${terms}"/>
<br/>
<spring:message code="fixUpTask.show.warranties.applicableLaws" var="laws"/>
<display:column property="applicableLaws" title="${laws}"/>
</display:table>
<br/>
<!-- Actions -->
<security:authorize access="hasRole('HANDYWORKER')">
<a href="customer/show.do?${fixUpTask.customer.id}"><spring:message code="fixUpTask.show.customer"/></a>
<br/>
<spring:message code="fixUpTask.show.customer.name"/>
<jstl:out value="${fixUpTask.customer.name}"/>
<br/>
<spring:message code="fixUpTask.show.customer.surname"/>
<jstl:out value="${fixUpTask.customer.surname}"/>
<br/>
<a href="application/handyWorker/create.do?${fixUpTask.id}">
<spring:message code="fixUpTask.show.applies"/></a>
<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.show.cancel" />"
		onclick="javascript: relativeRedir('fixUpTask/handyWorker/list.do');" />
</security:authorize>
<security:authorize access="hasRole('CUSTOMER')">
<input type="button" name="cancel"
		value="<spring:message code="fixUpTask.show.cancel"/>"
		onclick="javascript: relativeRedir('fixUpTask/customer/list.do')"/>
</security:authorize>

