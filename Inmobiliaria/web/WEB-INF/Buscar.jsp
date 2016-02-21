<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="margen">

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Criterios de búsqueda</a>

            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                <s:form cssClass="navbar-form" theme="simple" action="buscar">
                    <div class="form-group">
                        <label>Habitaciones</label>
                        <s:select cssClass="form-control" value="habitacion" name="habitacion" headerKey="-1" headerValue="Todos" list="{1,2,3,4,5}"/>
                    </div>
                    <div class="form-group">
                        <label>Baños</label>
                        <s:select cssClass="form-control" value="bano" name="bano" headerKey="-1" headerValue="Todos" list="{1,2,3,4,5}"/>
                    </div>
                    <div class="form-group">
                        <label>Tipo Propiedad</label>
                        <s:select cssClass="form-control" label="Tipo Propiedad" value="tipoPropiedad.idTipoPropiedad" name="tipoPropiedad.idTipoPropiedad" headerKey="-1" headerValue="Todos" list="tiposPropiedades" listKey="idTipoPropiedad" listValue="nombre"/>
                    </div>
                    <div class="form-group pull-right">
                        <s:submit value="Buscar" cssClass="btn btn-default"/>
                    </div>
                </s:form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div class=" col-md-12">
        <display:table name="propiedades" pagesize="1" requestURI="${buscar}" uid="row">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="paging.banner.item_name" value="propiedad" />
            <display:setProperty name="paging.banner.items_name" value="propiedades" />
            <display:setProperty name="paging.banner.one_item_found" value='<div class="text-center">  <span class="pagebanner">Una {0} encontrada. </span></div>' />
            <display:setProperty name="paging.banner.all_items_found" value='<div class="text-center">  <span class="pagebanner">{0} {1} encontradas. Mostrando {0}.</span></div>' />
            <display:setProperty name="paging.banner.some_items_found" value='<div class="text-center">  <span class="pagebanner">{0} {1} encontradas. Mostrando del {2} al {3}.</span></div>' />
            <display:setProperty name="basic.msg.empty_list" value="No se han encontrado propiedades"/>
            <%--<display:column property="idPropiedad" title="Id Propiedad"/>--%>   
            <display:column title="Imagen">
                <a href="<s:url action="ver" namespace="/">
                       <s:param name="idPropiedad"><s:property value="%{#attr.row.idPropiedad}"/></s:param>
                   </s:url>">
                    <img width="140" height="79" src="/Imagen?idImagen=<s:url value="%{#attr.row.imagenDefault.idImagenPropiedad}"/>">
                </a>
            </display:column>
            <display:column sortable="true"  property="tipoPropiedad.nombre" title="Tipo" />       
            <display:column sortable="true" property="codigoPropiedad" title="Codigo" class="hidden-xs" headerClass="hidden-xs"/>
            <display:column sortable="false" property="direccion" title="Dirección" class="hidden-xs" headerClass="hidden-xs" />       
            <display:column sortable="true" property="terreno" title="Terreno" class="hidden-xs" headerClass="hidden-xs"/>       
            <display:column sortable="true" property="edificado" title="Edificado" class="hidden-xs" headerClass="hidden-xs"/>       
            <display:column sortable="true" property="habitacion" title="Habitaciones" />       
            <display:column sortable="true" property="bano" title="Baños" class="hidden-xs" headerClass="hidden-xs"/>       
        </display:table>
    </div>
</div>
