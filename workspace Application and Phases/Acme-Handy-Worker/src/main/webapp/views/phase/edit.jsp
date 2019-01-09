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
<form:form action="phase/handyworker/edit.do" modelAttribute="phase">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<jstl:if test="${phase.id != 0}">
		<form:hidden path="fixUpTask"/>
	</jstl:if>
	<jstl:if test="${phase.id == 0}">
		<form:label path="fixUpTask">
			<spring:message code="handyWorker.fixUpTask" />:
		</form:label>
		<form:select id="fixUpTasks" path="fixUpTask">
			<form:options items="${fixUpTasks}" itemLabel="address" itemValue="id"/>
			<form:option value="0" label="----"/>
		</form:select>
		<form:errors cssClass="error" path="fixUpTask" />
		<br/>
	</jstl:if>
	
		<form:label path="title">
			<spring:message code="phase.title" />:
		</form:label>
		<form:input path="title"/>
		<form:errors cssClass="error" path="title" />
		<br/>
	
	<form:label path="description">
			<spring:message code="phase.description" />:
		</form:label>
		<form:input path="description"/>
		<form:errors cssClass="error" path="description" />
		<br/>
		
		<form:label path="startMoment">
			<spring:message code="phase.startMoment" />:
		</form:label>
		<form:input path="startMoment"/>
		<form:errors cssClass="error" path="startMoment" />
		<br/>
		
		<form:label path="endMoment">
			<spring:message code="phase.endMoment" />:
		</form:label>
		<form:input path="endMoment"/>
		<form:errors cssClass="error" path="endMoment" />
		<br/>
		
		<form:label path="number">
			<spring:message code="phase.number" />:
		</form:label>
		<form:input path="number"/>
		<form:errors cssClass="error" path="number" />
		<br/>
		
		<input type="submit" name="save"
		value="<spring:message code="phase.save" />" />&nbsp; 
	<jstl:if test="${phase.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="phase.delete" />"
			onclick="return confirm('<spring:message code="phase.confirm.delete" />')" />&nbsp;
	</jstl:if>
</form:form>
<input type="button" name="cancel"
		value="<spring:message code="phase.cancel" />"
		onclick="javascript: relativeRedir('phase/handyworker/list.do');" />
	<br />
</security:authorize>