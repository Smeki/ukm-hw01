<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>

<script src="https://code.jquery.com/jquery-3.1.1.js" integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
    $( ".note" ).draggable();
  } );
</script>

<m:Base title="Notes">
    <div id="input_form">
    <form action="<c:url value='/add' />" method="POST">
        <label for="title">Title</label>
        <input type="text" name="title" />
        <br>
        <textarea name="content" cols="40" rows="5"></textarea>
        <br>
        <input type="hidden" name="createType" value="[WEB GUI]" />
        <br>
        <label for="author">Author</label>
        <input type="text" name="author" />
        <br>
        <input value="Add" type="submit" />
    </form>
        </div>

    <c:if test="${param.warning}">
        <span>You must fill in both fields.<br></span>
    </c:if>

    <c:choose>
        <c:when test="${not empty notes}">
            <c:forEach items="${notes}" var="note" varStatus="vs">
                <div class="note">
                    <div class="note_header">
                    <div class="title"><c:out value="${note.title}"/></div>
                    <div class="createType">Created: <c:out value="${note.createType}"/></div><br>
                    <div class="author">Author: <c:out value="${note.author}"/></div>
                    <div class="buttons">
                        <form method="GET" action="<c:url value='/edit'/>">
                            <input type="hidden" value="${note.id}" name="id" />
                            <input type="submit" value="Edit"/>
                        </form>                               
                        <form method="POST" action="<c:url value='/delete'/>">
                            <input type="hidden" value="${note.id}" name="id" />
                            <input type="submit" value="Delete"/>
                        </form>                               
                    </div>
                            </div>
                    <div class="content"><c:out value="${note.content}"/></div>
                    
                    
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <span>No note added so far.</span>
        </c:otherwise>
    </c:choose>
</m:Base>