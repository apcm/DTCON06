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
<jstl:set var="finder1" value="${keyWord }"/>
<jstl:if test="${finder1!='expired12345678912345689123456789'}">
	<h2>
		<spring:message code="finder.keyWord" />:
	</h2>
	<jstl:out value="${finder.keyWord}"></jstl:out>
	
	<h2>
		<spring:message code="finder.category" />:
	</h2>
	<jstl:out value="${finder.category.name}"></jstl:out>
	
	<h2>
		<spring:message code="finder.minPrice" />:
	</h2>
	<jstl:out value="${finder.minPrice.amount}"></jstl:out>
	<jstl:out value="${finder.minPrice.currency}"></jstl:out>
	
	<h2>
		<spring:message code="finder.maxPrice" />:
	</h2>
	<jstl:out value="${finder.maxPrice.amount}"></jstl:out>
	<jstl:out value="${finder.maxPrice.currency}"></jstl:out>
	
	<h2>
		<spring:message code="finder.startDate" />:
	</h2>
	<jstl:out value="${finder.startDate}"></jstl:out>
	
	<h2>
	<spring:message code="finder.endDate" />:
	</h2>
	<jstl:out value="${finder.endDate}"></jstl:out>
	
	<h2>
	<spring:message code="finder.warranty" />:
	</h2>
	<h3>
	<spring:message code="finder.warranty.title" />:
	</h3>
	<jstl:out value="${finder.warranty.title}"></jstl:out>	
	<h3>
	<spring:message code="finder.warranty.terms" />:
	</h3>
	<jstl:out value="${finder.warranty.terms}"></jstl:out>
	<h3>
	<spring:message code="finder.warranty.applicableLaws" />:
	</h3>
	<jstl:out value="${finder.warranty.applicableLaws}"></jstl:out>
	<h3>
	<spring:message code="finder.warranty.finalMode" />:
	</h3>
	<jstl:set var="finalMode" value="${finder.warranty.finalMode}"/>
	<jstl:if test="${finalMode ==true}">
	<spring:message code="finder.warranty.finalMode.yes"/>
	</jstl:if>
	<jstl:if test="${finalMode ==false}">
	<spring:message code="finder.warranty.finalMode.no"/>
	</jstl:if>
	
	<h3>
	<spring:message code="finder.results" />:
	</h3>
<!-- 	<jstl:forEach var="res" items="${finder.fixUpTasks}">
		<jstl:out value="${res}"/>
		<a href="finder/handyworker/edit.do?finderId=${finder.id}" ><spring:message code="finder.edit" /></a>
	</jstl:forEach>  -->

	<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="finder.fixUpTasks" requestURI="${requestURI}" id="row">
	
	<display:column property="ticker" titleKey="fixUpTask.ticker" />
	<display:column property="description" titleKey="fixUpTask.description" />
	<display:column property="maximumPrice.amount" titleKey="fixUpTask.maximumPrice" />
	<display:column property="maximumPrice.currency" titleKey="fixUpTask.currency" />
	<display:column property="startDate" titleKey="fixUpTask.startDate" />
	<display:column property="endDate" titleKey="fixUpTask.endDate" />
	
	<display:column>
		<a href="fixUpTask/handyworker/show.do?fixUpTaskId=${fixUpTask.id}"> <spring:message
					code="fixUpTask.show.link" />
		</a>
	</display:column>

</display:table>
	
	<p>
	<a href="finder/handyworker/edit.do?finderId=${finder.id}" ><spring:message code="finder.edit" /></a>
	</p>	
	
	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="finder.back" />" />
	</jstl:if>
</security:authorize>

<jstl:set var="finder1" value="${keyWord }"/>
<jstl:if test="${finder1=='expired12345678912345689123456789'}">
<h1><spring:message code="finder.gone"/>
</h1>
<p><spring:message code="finder.createNewOne"/>
<br/>
<a href="finder/handyworker/edit.do?finderId=${finder.id}">
<spring:message code="finder.click"/></a></p>
</jstl:if>