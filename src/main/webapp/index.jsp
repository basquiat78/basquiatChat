<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="operationMode" expression="@conf.getProperty('operation.mode')" />
<c:choose>
	<c:when  test="${operationMode eq 'stage'}">
		<c:redirect context="/" url="/"/>
	</c:when >
	<c:otherwise>
		<c:url var="devRoot" value="/login.do" />
		<c:redirect url="/login.do"/>
	</c:otherwise>
</c:choose>