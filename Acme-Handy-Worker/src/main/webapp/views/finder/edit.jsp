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

	<form:label path="warranty">
		<spring:message code="finder.warranty" />:
	</form:label>
	<form:select id="warranties" path="warranty">
	<form:options items="${warranties}" itemLabel="title" itemValue="id" />
	<form:option value="0" label="----" />
	</form:select>
	<form:errors cssClass="error" path="warranty" />
	<br />
	
	<form:label path="category">
		<spring:message code="finder.category" />:
	</form:label>
	<form:select id="categories" path="category">
	<form:options items="${categories}" itemLabel="name" itemValue="id" />
	<form:option value="0" label="----" />
	</form:select>
	<form:errors cssClass="error" path="category" />
	<br />
		
	<form:label path="minPrice.amount">
		<spring:message code="finder.minPrice" />:
	</form:label>
	<form:input path="minPrice.amount" />
	<form:errors cssClass="error" path="minPrice.amount" />
	<br />

	<form:label path="minPrice.currency">
		<spring:message code="finder.minPrice" />:
	</form:label>
	<form:input path="minPrice.currency" />
	<form:errors cssClass="error" path="minPrice.currency" />
	<br />

	<form:label path="maxPrice.amount">
		<spring:message code="finder.maxPrice" />:
	</form:label>
	<form:input path="maxPrice.amount" optional="true"/>
	<form:errors cssClass="error" path="maxPrice.amount" />
	<br />
	
	<form:label path="startDate">
		<spring:message code="finder.startDate" />:
	</form:label>
	<form:input path="startDate" />
	<form:errors cssClass="error" path="startDate" />
	<br />

	<form:label path="maxPrice.currency">
		<spring:message code="finder.maxPrice" />:
	</form:label>
	<form:input path="maxPrice.currency" optional="true"/>
	<form:errors cssClass="error" path="maxPrice.currency" />
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