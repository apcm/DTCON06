<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="fixuptasks" requestURI="${requestURI}" id="row">
<!-- Attributes -->
<spring:message code="fixUpTask.list.ticker" var="tickerHeader"/>
<display:column property="ticker" title="${tickerHeader}"/>

<spring:message code="fixUpTask.list.moment" var="momentHeader" />
<display:column property="moment" title="${momentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

<spring:message code="fixUpTask.list.description" var="descriptionHeader"/>
<display:column property="description" title="${descriptionHeader}"/>

<spring:message code="fixUpTask.list.category" var="categoryHeader"/>
<display:column property="category" title="${categoryHeader}"/>

<!-- Action Links -->

<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="fixUpTask/customer/edit.do?fixUpTaskId=${row.id}">
				<spring:message	code="fixUpTask.list.edit" />
			</a>
		</display:column>		
	</security:authorize>
</display:table>

<security:authorize access="hasRole('CUSTOMER')">
	<div>
		<a href="fixUpTask/customer/create.do"> <spring:message
				code="fixUpTask.create" />
		</a>
	</div>
</security:authorize>
