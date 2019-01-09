<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="phases" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	<spring:message code="phase.title" var="title"/>
	<display:column property="title" title="${title}"/>
	
	<spring:message code="phase.description" var="description"/>
	<display:column property="description" title="${description}"/>	
	
	<spring:message code="phase.startMoment" var="startMoment"/>
	<display:column property="startMoment" title="${startMoment}"/>
	
	<spring:message code="phase.endMoment" var="endMoment"/>
	<display:column property="endMoment" title="${endMoment}"/>
	
	<spring:message code="phase.number" var="number"/>
	<display:column property="number" title="${number}"/>
	
	<!-- Action Links -->
	
		<display:column>
			<a href="phase/handyworker/edit.do?phaseId=${row.id}">
				<spring:message	code="phase.edit" />
			</a>
		</display:column>	
</display:table>
<br/>
		<div>
		<a href="phase/handyworker/create.do"> <spring:message
				code="phase.create" />
		</a>
	</div>
</security:authorize>
	

