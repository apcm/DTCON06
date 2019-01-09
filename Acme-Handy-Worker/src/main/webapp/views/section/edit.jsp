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
<form:form action="section/handyWorker/edit.do" modelAttribute="section">
<form:hidden path="id"/>
<form:hidden path="version"/>

<form:label path="title">
	<spring:message code="section.title"/>
</form:label>
<form:input path="title"/>
<form:errors cssClass="error" path="title" />
<br/>

<form:label path="text">
	<spring:message code="section.text"/>
</form:label>
<form:input path="text"/>
<form:errors cssClass="error" path="text" />
<br/>

<form:label path="number">
	<spring:message code="section.number"/>
</form:label>
<form:input path="number"/>
<form:errors cssClass="error" path="number" />
<br/>

<form:label path="photoUrl">
	<spring:message code="section.photoUrl"/>
</form:label>
<form:input path="photoUrl"/>
<form:errors cssClass="error" path="photoUrl" />
<br/>

<!-- Actions -->
<input type="submit" name="save"
		value="<spring:message code="section.save" />" />&nbsp; 
	<jstl:if test="${section.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="section.delete" />"
			onclick="return confirm('<spring:message code="section.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="section.cancel" />"
		onclick="javascript: relativeRedir('section/list.do');" />
	<br />
</form:form>
</security:authorize>