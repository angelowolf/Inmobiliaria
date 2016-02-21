<%-- 
    Document   : modal-delete
    Created on : 07/02/2016, 20:25:58
    Author     : flor
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-bootstrap-tags" prefix="sb" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="modal fade" id="modal-delete" tabindex="-1" role="dialog" aria-labelledby="Eliminar" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="Eliminar">Atención</h4>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <s:form theme="simple" action="eliminar" id="deleteURL" style="display:inline-block;">
                    <s:hidden name="id" />
                    <s:submit theme="simple" value="Eliminar" action="eliminar" cssClass="btn btn-warning"/>
                </s:form>
            </div>
        </div>
    </div>
</div>