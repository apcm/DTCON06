<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
	<h1>
		<spring:message code="box.list" />
	</h1>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="boxes" requestURI="${requestURI}" id="row">

<!-- Attributes -->
		<spring:message code="box.name" var="boxName"/>
		<display:column property="name" titleKey="${boxName}" sortable="true"/>
<!-- Actions -->
		<display:column>
			<a href="message/list.do?boxId=${row.id}"> 
				<spring:message	code="box.select" />
			</a>
		</display:column>
		
		<jstl:if test="${row.predefined != 'true'}">
		<display:column>
			<a href="box/edit.do?boxId=${box.id}"> 
				<spring:message	code="box.edit" />
			</a>
		</display:column>
		</jstl:if>
	</display:table>

<div>
	<a href="box/create.do"> 
	<spring:message	code="box.create" />
	</a>
</div>
<div>
	<a href="message/create.do">
	<spring:message code="message.create"/>
	</a>
</div>
</security:authorize>