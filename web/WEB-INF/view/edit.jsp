<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>

<m:Base title="Notes">
    <div id="edit_form">
        <form action="<c:url value='/save' />" method="POST">
            <input type="hidden" name="id" value="${note.id}" />
            <label for="title">Title</label>
            <input type="text" name="title" value="<c:out value='${note.title}' />" />
            <br>
            <textarea name="content" cols="40" rows="5"><c:out value='${note.content}' /></textarea>
            <br>
            <input type="hidden" name="createType" value="<c:out value='${note.createType}'/>" />
            <br>
            <label for="author">GitMessingVolume One</label>
            <label for="author">Author</label>
            <input type="text" name="author" value="<c:out value='${note.author}'/>" />
            <br>
            <input value="Edit" type="submit" />
        </form>
        </div>

    <c:if test="${param.warning}">
        <span>You must fill in both fields.</span>
        <span>SPAN FULL OF NOPES</span>
    </c:if>
</m:Base>