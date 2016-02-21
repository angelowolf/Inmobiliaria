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
        Formulario Propiedades Destacadas
    </legend>
</fieldset>
<div class="form-group col-md-8 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form id="form" style="padding-top: 25px;" action="guardar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <s:hidden name="destacado.idDestacado"/>
                <div class="col-md-8 col-md-offset-2">
                    <s:textfield name="destacado.nombre" label="Título" placeholder="Ingrese el título" />
                    <s:select value="destacado.propiedad.idPropiedad" label="Propiedad" name="destacado.propiedad.idPropiedad" headerKey="-1" headerValue="Seleccione" list="propiedadesLista" listKey="idPropiedad" listValue="codigoPropiedad"/>
                    <s:file name="upload" label="Imagen:" />
                </div>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
            </s:form>
        </div>
    </div>
</div>