<%@page contentType="application/json; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

{
	"title":"${title}",
	"content":"${content}",
	"originalfilename":"${originalfilename}",
	"savedfilename":"${savedfilename}",
	"filecontenttype":"${filecontenttype}"
}