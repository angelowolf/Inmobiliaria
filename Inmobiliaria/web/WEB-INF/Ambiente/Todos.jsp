<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<div class="form-group">
    <div class="text-center">
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>
    </div>
    <fieldset>
        <legend>
            Ambientes
            <div class="pull-right">
                <s:form action="nuevo" >
                    <s:submit value="Nuevo" cssClass="btn btn-info"/>
                </s:form>
            </div>
        </legend>
    </fieldset>  
    <div class=" col-md-12">
        <display:table name="ambienteLista" pagesize="10" requestURI="${listar}" uid="row">
            <display:setProperty name="basic.msg.empty_list" >Aún no hay ambientes registrados. Puede crear uno desde <a href="<s:url action='nuevo' namespace='/Ambiente'/>">aquí</a>.</display:setProperty>
            <display:column property="idAmbiente" title="Numero Ambiente"/>
            <display:column sortable="true" property="nombre" title="Nombre"/>
            <display:column title="Funciones">
                <s:form action="editar"  style="display:inline-block;">
                    <s:hidden name="idAmbiente" value="%{#attr.row.idAmbiente}"/>
                    <button value="Editar" title="Editar" class="btn btn-info"><i class='fa fa-pencil-square-o'></i></button>
                    </s:form>
                    <s:submit type="button" data-id="%{#attr.row.idAmbiente}" title="Eliminar" theme="simple" cssClass="btn btn-danger" data-toggle="modal" data-target="#modal-delete"><i class='fa fa-trash'></i></s:submit>
            </display:column>
        </display:table>

    </div>
</div>