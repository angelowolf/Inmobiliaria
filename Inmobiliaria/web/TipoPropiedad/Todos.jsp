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
            Tipo Propiedades
            <div class="pull-right">
                <s:form action="nuevo" >
                    <s:submit value="Nuevo" cssClass="btn btn-info"/>
                </s:form>
            </div>
        </legend>
    </fieldset>  
    <div class=" col-md-12">
        <display:table name="tipoPropiedadLista" pagesize="10" requestURI="${listar}" uid="row">
            <display:setProperty name="basic.msg.empty_list" >Aún no hay tipos de propiedad registrados. Puede crear uno desde <a href="<s:url action='nuevo' namespace='/TipoPropiedad'/>">aquí</a>.</display:setProperty>
            <display:column property="idTipoPropiedad" title="Numero Tipo Propiedad"/>
            <display:column sortable="true" property="nombre" title="Nombre"/>
            <display:column title="Funciones">
                <s:form action="editar" id="editURL" style="display:inline-block;">
                    <s:hidden name="idTipoPropiedad" value="%{#attr.row.idTipoPropiedad}"/>
                    <s:submit value="Editar" action="%{editURL}" cssClass="btn btn-info"/>
                </s:form>
                <s:form action="eliminar" id="deleteURL" style="display:inline-block;">
                    <s:hidden name="idTipoPropiedad" value="%{#attr.row.idTipoPropiedad}"/>
                    <s:submit value="Eliminar" action="%{deleteURL}" cssClass="btn btn-danger"/>
                </s:form>
            </display:column>
        </display:table>

    </div>
</div>