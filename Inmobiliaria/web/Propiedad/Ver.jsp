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
            <s:iterator value="propiedad.imagenes" var="imagen" status="stat">
                <s:if test="#stat.index == 0">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    </s:if>
                    <s:else>
                    <li data-target="#myCarousel" data-slide-to="<s:property value="#stat.index"/>"></li>
                    </s:else>
                </s:iterator>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <s:iterator value="propiedad.imagenes" var="imagen" status="stat">
                <s:if test="#stat.index == 0">
                    <div class="item active">
                        <img src="<s:url value="/%{#imagen.ruta}"/>"/>
                    </div></s:if>
                <s:else>
                    <div class="item">
                        <img src="<s:url value="/%{#imagen.ruta}"/>"/>
                    </div> </s:else>
            </s:iterator>          
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        </a>
    </div>


</div>