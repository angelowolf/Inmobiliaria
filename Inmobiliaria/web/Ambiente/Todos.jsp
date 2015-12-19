<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<div class="form-group">
    <s:actionmessage theme="bootstrap"/>
    <s:actionerror theme="bootstrap"/>
    <fieldset>
        <legend>
            Ambientes
        </legend>
    </fieldset>  
    <div class=" col-md-12">
        <display:table name="ambienteLista" pagesize="10" requestURI="${listar}" uid="row">
            <display:column property="idAmbiente" title="Numero Ambiente"/>
            <display:column sortable="true" property="nombre" title="Nombre"/>
            <display:column title="Funciones">
                <s:form action="editar" id="editURL" style="display:inline-block;">
                    <s:hidden name="idAmbiente" value="%{#attr.row.idAmbiente}"/>
                    <s:submit value="Editar" action="%{editURL}" cssClass="btn btn-info"/>
                </s:form>
                <s:form action="eliminar" id="deleteURL" style="display:inline-block;">
                    <s:hidden name="idAmbiente" value="%{#attr.row.idAmbiente}"/>
                    <s:submit value="Eliminar" action="%{deleteURL}" cssClass="btn btn-danger"/>
                </s:form>
            </display:column>
        </display:table>

    </div>
</div>