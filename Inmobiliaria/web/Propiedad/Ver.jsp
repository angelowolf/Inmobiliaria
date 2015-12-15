<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class=" col-md-8">   
    <ul class="col-md-6">
        <s:iterator value="propiedad.servicios" var="servicio">
            <li>
                <i class="fa fa-check"></i> <s:property value="#servicio.nombre" />
            </li>
        </s:iterator>

    </ul>
    <s:property value="propiedad.nombre" />
    <s:property value="propiedad.codigoPropiedad" />
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <s:iterator value="propiedad.imagenes" var="imagen">
                <div class="item">
                    <img src="<s:url value="/%{#imagen.ruta}"/>"/>
                </div>

            </s:iterator>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


</div>