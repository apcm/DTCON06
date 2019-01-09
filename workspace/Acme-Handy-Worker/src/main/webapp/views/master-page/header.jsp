<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme Handy Worker Co., Inc." /></a>
</div>
<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv"><spring:message	code="master.page.anon" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/register.do"><spring:message code="master.page.anon.register.customer" /></a></li>
					<li><a href="handyworker/register.do"><spring:message code="master.page.anon.register.handyworker" /></a></li>		
					<li><a href="sponsor/register.do"><spring:message code="master.page.anon.register.sponsor" /></a></li>
					<li><a href="tutorial/list.do"><spring:message code="master.page.tutorials" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/customer/edit.do"><spring:message code="master.page.customer.edit" /></a></li>
					<li><a href="box/list.do"><spring:message code="master.page.boxes" /></a></li>					
					<li><a href="customer/show.do"><spring:message code="master.page.customer.show" /></a></li>					
					<li><a href="fixUpTask/customer/list.do"><spring:message code="master.page.fixuptasks" /></a></li>					
					<li><a href="application/customer/list.do"><spring:message code="master.page.applications" /></a></li>					
					<li><a href="complaint/list.do"><spring:message code="master.page.complaints" /></a></li>					
					<li><a href="endorsement/list.do"><spring:message code="master.page.endorsements" /></a></li>					
				</ul>
			</li>
		</security:authorize>
	
		<security:authorize access="hasRole('HANDYWORKER')">
			<li><a class="fNiv"><spring:message	code="master.page.handyworker" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="handyworker/handyworker/edit.do"><spring:message code="master.page.handyworker.edit" /></a></li>
					<li><a href="box/list.do"><spring:message code="master.page.boxes" /></a></li>					
					<li><a href="handyworker/show.do"><spring:message code="master.page.handyworker.show" /></a></li>	
					<li><a href="fixUpTask/handyWorker/list.do"><spring:message code="master.page.catalogue"/></a></li>				
					<li><a href="phase/handyWorker/list.do"><spring:message code="master.page.phases" /></a></li>					
					<li><a href="application/handyWorker/list.do"><spring:message code="master.page.applications" /></a></li>					
					<li><a href="finder/handyworker/show.do"><spring:message code="master.page.finder" /></a></li>					
					<li><a href="complaint/list.do"><spring:message code="master.page.complaints" /></a></li>					
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsor/edit.do"><spring:message code="master.page.sponsor.edit" /></a></li>
					<li><a href="box/list.do"><spring:message code="master.page.boxes" /></a></li>					
					<li><a href="sponsor/show.do"><spring:message code="master.page.sponsor.show" /></a></li>					
					<li><a href="sponsorship/list.do"><spring:message code="master.page.sponsorships" /></a></li>		
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('REFEREE')">
			<li><a class="fNiv"><spring:message	code="master.page.referee" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="referee/edit.do"><spring:message code="master.page.referee.edit" /></a></li>
					<li><a href="box/list.do"><spring:message code="master.page.boxes" /></a></li>					
					<li><a href="referee/show.do"><spring:message code="master.page.referee.show" /></a></li>					
					<li><a href="complaint/list.do"><spring:message code="master.page.complaints" /></a></li>		
					<li><a href="report/list.do"><spring:message code="master.page.reports" /></a></li>		
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
					<li><a href="box/list.do"><spring:message code="master.page.boxes" /></a></li>					
					<li><a href="administrator/show.do"><spring:message code="master.page.administrator.show" /></a></li>					
					<li><a href="administrator/administrator/create.do"><spring:message code="master.page.administrator.register" /></a></li>		
					<li><a href="referee/register.do"><spring:message code="master.page.referee.register" /></a></li>	
					<li><a href="warranty/administrator/list.do"><spring:message code="master.page.warranties" /></a></li>	
					<li><a href="category/administrator/list.do"><spring:message code="master.page.categories" /></a></li>	
					<li><a href="dashboard/show.do"><spring:message code="master.page.dashboard" /></a></li>	
					<li><a href="suspactors/list.do"><spring:message code="master.page.suspactors" /></a></li>	
					<li><a href="score/show.do"><spring:message code="master.page.score" /></a></li>	
					<li><a href="wordlist/show.do"><spring:message code="master.page.wordlist" /></a></li>	
						
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"></a><li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
	</ul>
</div>
<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

