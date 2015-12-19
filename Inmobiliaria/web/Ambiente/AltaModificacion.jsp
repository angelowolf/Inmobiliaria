<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fieldset>
    <legend>
        Formulario Ambiente
    </legend>
</fieldset>
<div class="form-group col-md-6 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form  style="padding-top: 25px;" action="guardarOModificar" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="ambiente.idAmbiente"/>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="ambiente.nombre" label="Nombre" placeholder="Ingrese el nombre del ambiente" />
                </div>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
                <%--</s:push>--%>            
            </s:form>
        </div>
    </div>
</div>