<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="text-center">
    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>
</div>
<fieldset>
    <legend>
        Formulario Tipo Moneda
    </legend>
</fieldset>
<div class="form-group col-md-6 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form  style="padding-top: 25px;" action="guardarOModificar" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="tipoMoneda.idTipoMoneda"/>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="tipoMoneda.nombre" label="Nombre" placeholder="Ingrese el nombre del tipo de moneda" />
                    <s:textfield name="tipoMoneda.sigla" label="Sigla" placeholder="Ingrese la sigla del tipo de moneda" />
                </div>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
                <%--</s:push>--%>            
            </s:form>
        </div>
    </div>
</div>