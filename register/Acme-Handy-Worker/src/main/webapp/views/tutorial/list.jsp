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
	name="tutorial" requestURI="${requestURI}" id="row">
	<!-- Attributes -->
	<spring:message code="tutorial.title" var= "title"/>
	<display:column property="title" title="${title}"/>
	<br/>
	
	<spring:message code="tutorial.lastUpdate" var= "lastUpdate"/>
	<display:column property="lastUpdate" title="${lastUpdate}"/>
	<br/>
	
	<!-- Actions -->
	<display:column>
	<a href="handyWorker/show.do?handyWorkerId=${row.handyWorker.id}">
	<spring:message code="tutorial.handyWorker"/>
	</a>
	</display:column>
	
	<display:column>
	<a href="section/list.do?handyWorkerId=${row.id}">
	<spring:message code="tutorial.show"/>
	</a>
	</display:column>
	
	<security:authorize access="hasRole('HANDYWORKER')">
			<display:column>
			<a href="tutorial/handyWorker/edit.do?tutorialId=${row.id}">
				<spring:message	code="tutorial.edit" />
			</a>
	</display:column>
	</security:authorize>
	
</display:table>