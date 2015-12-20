<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:actionerror theme="bootstrap"/>
<style type="text/css">
    .form-inline .form-group{
        margin-left: 0;
        /*margin-right: 0;*/
    }
    .input{
        padding-top: 10px;
    }
</style>
<fieldset>
    <legend>
        Modificar Propiedad
    </legend>
</fieldset>
<s:actionerror theme="bootstrap"/>
<s:actionmessage theme="bootstrap"/>
<s:fielderror theme="bootstrap"/>
<div class="form-group col-md-8 col-md-offset-2">   
    <div class="panel panel-default">
        <div class="panel-heading">
            <s:form action="modificar" method="POST" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal">
                <%--<s:push value="candidato">--%>
                <s:hidden name="propiedad.idPropiedad"/>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.nombre" label="Nombre" placeholder="Ingrese el nombre de la propiedad" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.codigoPropiedad" label="Codigo" placeholder="Ingrese el codigo de la propiedad" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.habitacion" label="Habitaciones" placeholder="Ingrese la cantidad de habitaciones" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.baño" label="Baños" placeholder="Ingrese la cantidad de baños" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.terreno" label="Terreno" placeholder="Ingrese la superficie del terreno" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textfield name="propiedad.edificado" label="Edificado" placeholder="Ingrese la superficie construida" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:textarea name="propiedad.detalle" label="Detalles" placeholder="Ingrese algun detalle" />
                </div>
                <div class="col-md-8 col-md-offset-2 input">
                    <s:checkboxlist label="Servicios" value="serviciosDefault" list="todosServicios" name="serviciosElegidos" listKey="idServicio" listValue="nombre"/>
                </div> 
                <div class="col-md-8 col-md-offset-2 input">
                    <s:checkboxlist label="Ambientes" value="ambientesDefault" list="todosAmbientes" name="ambientesElegidos" listKey="idAmbiente" listValue="nombre"/>
                </div> 

                <div class="col-md-8 col-md-offset-2">
                    <h3 class="text-center">Imagenes Guardadas</h3>
                    <div id="contenedor-checks">
                        <s:iterator value="todosImagenes" var="imagen" status="stat">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="form-group">
                                    <div class="form-inline">
                                        <img width="140" height="79" src="/Inmobiliaria/Imagen?idImagen=<s:url value="%{#imagen.idImagenPropiedad}"/>" />
                                        <s:checkbox name="imagenesElegidos" value="true" fieldValue="%{idImagenPropiedad}" label="Guardar" id="%{#stat.count}">
                                        </s:checkbox>
                                    </div>
                                </div>
                            </div>
                        </s:iterator>
                    </div>
                </div>
                <div class="col-md-8 col-md-offset-2" id="imagenes">
                    <h3 class="text-center">Imagenes Nuevas</h3>
                    <s:file id="imagen1" name="imagen"  label="Imagen 1: "/>
                </div>
                <div class="col-md-3 col-md-offset-3">
                    <button id="btnAgregar" class="btn btn-warning ">Agregar Imagen</button>
                </div>
                <%--<s:file name="imagen" label="Imagen"/>--%>
                <s:submit value="Confirmar" cssClass="btn btn-success col-md-offset-9 col-md-3"/>
                <%--</s:push>--%>         
            </s:form>
        </div>
    </div>
</div>