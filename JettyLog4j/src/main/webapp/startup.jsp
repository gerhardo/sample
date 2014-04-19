<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Startseite</title>
</head>
<body>
<h1>Startseite</h1>
<% Logger LOG = LoggerFactory.getLogger("de.emediapark.sample.jettylog4j"); LOG.info("Startseite aufgerufen"); %>
</body>
</html>