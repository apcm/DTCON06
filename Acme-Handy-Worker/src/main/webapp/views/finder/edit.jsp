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

<security:authorize access="hasRole('HANDYWORKER')">
	<h1>
		<spring:message code="finder.edit.header" />
	</h1>

<form:form action="finder/handyworker/edit.do" modelAttribute="finder">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="fixUpTasks"	/>

	<form:label path="keyWord">
		<spring:message code="finder.keyWord" />:
	</form:label>
	<form:input path="keyWord" />
	<form:errors cssClass="error" path="keyWord" />
	<br />

	<form:label path="category">
		<spring:message code="finder.category" />:
	</form:label>
	<%import java.util.List;
	List<String> category=new ArrayList<String>(); %>
	<form:select path="category" />
	<form:errors cssClass="error" path="category" />
	<br />

	<form:label path="minPrice">
		<spring:message code="finder.minPrice" />:
	</form:label>
	<form:input path="minPrice" />
	<form:errors cssClass="error" path="minPrice" />
	<br />

	<form:label path="maxPrice">
		<spring:message code="finder.maxPrice" />:
	</form:label>
	<form:input path="maxPrice" />
	<form:errors cssClass="error" path="maxPrice" />
	<br />
	
	<form:label path="startDate">
		<spring:message code="finder.startDate" />:
	</form:label>
	<form:input path="startDate" />
	<form:errors cssClass="error" path="startDate" />
	<br />
	
	<form:label path="endDate">
		<spring:message code="finder.endDate" />:
	</form:label>
	<form:input path="endDate" />
	<form:errors cssClass="error" path="endDate" />
	<br />


	<input type="submit" name="save"
		value="<spring:message code="finder.save" />" />&nbsp; 
		
	<input type="button" name="cancel"
		value="<spring:message code="finder.cancel" />"
		onclick="javascript: relativeRedir('finder/handyworker/show.do');" />
	<br />


</form:form>
</security:authorize>