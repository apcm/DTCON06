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
	name="section" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	<spring:message code="section.title" var= "title"/>
	<display:column property="title" title="${title}"/>
	<br/>
	
	<spring:message code="section.text" var= "text"/>
	<display:column property="text" title="${lastUpdate}"/>
	<br/>
	
	<!-- Actions -->
			<display:column>
			<a href="section/handyWorker/edit.do?sectionId=${row.id}">
				<spring:message	code="section.edit" />
			</a>
	</display:column>

</display:table>
</security:authorize>