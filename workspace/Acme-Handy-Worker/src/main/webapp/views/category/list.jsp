<%@page import="domain.Category"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('ADMIN')">

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="categories" requestURI="${requestURI}" id="row">

	
	<display:column property="name" titleKey="category.name" />
	
 	<display:column property="parentCategory" titleKey="category.parentCategory" />

	<display:column>	
			<a href="category/administrator/edit.do?categoryId=${row.id}">
			<spring:message code="warranty.edit.link" />
			</a>

	</display:column>
		
</display:table>

<div>
	<a href="category/administrator/create.do"> <spring:message
			code="category.list.create" />
	</a>
</div>
</security:authorize>