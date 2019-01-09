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
<form:form action="tutorial/handyWorker/edit.do" modelAttribute="tutorial">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="sections"/>
<form:hidden path="handyWorker"/>

<form:label path="title">
	<spring:message code="tutorial.title"/>
</form:label>
<form:input path="title"/>
<form:errors cssClass="error" path="title" />
<br/>

<form:label path="lastUpdate">
	<spring:message code="tutorial.lastUpdate"/>
</form:label>
<form:input path="lastUpdate"/>
<form:errors cssClass="error" path="lastUpdate" />
<br/>

<form:label path="summary">
	<spring:message code="tutorial.summary"/>
</form:label>
<form:input path="summary"/>
<form:errors cssClass="error" path="summary" />
<br/>

<form:label path="photoUrl">
	<spring:message code="tutorial.photoUrl"/>
</form:label>
<form:input path="photoUrl"/>
<form:errors cssClass="error" path="photoUrl" />
<br/>

<!-- Actions -->
<input type="submit" name="save"
		value="<spring:message code="tutorial.save" />" />&nbsp; 
	<jstl:if test="${tutorial.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="tutorial.delete" />"
			onclick="return confirm('<spring:message code="tutorial.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="tutorial.cancel" />"
		onclick="javascript: relativeRedir('tutorial/list.do');" />
	<br />
</form:form>
</security:authorize>