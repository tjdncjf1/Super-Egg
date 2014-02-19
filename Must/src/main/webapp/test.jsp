<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:url var="url" value="http://openapi.naver.com/search" >
    <c:param name="key" value="c1b406b32dbbbbeee5f2a36ddc14067f" />
    <c:param name="query" value="노트북" />
    <c:param name="display" value="5" />
    <c:param name="start" value="1" />
    <c:param name="target" value="shop" />
    <c:param name="sort" value="sim" />
</c:url>
<c:import url="${url}"></c:import>
</body>
</html>