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
        Formulario Ambiente
    </legend>
</fieldset>
<div class="form-group col-md-8 col-md-offset-2">       
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form theme="bootstrap"  style="padding-top: 25px;" action="guardarOModificar" validate="true" enctype="multipart/form-data"  cssClass="form-horizontal">
                <s:hidden name="ambiente.idAmbiente"/>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="ambiente.nombre" label="Nombre" placeholder="Ingrese el nombre del ambiente" />
                </div>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-10 col-md-2"/>
            </s:form>
        </div>
    </div>
</div>