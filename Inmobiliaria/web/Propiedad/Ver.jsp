<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">
    #wrap {
        padding:2em;
    }
    .panel.panel-horizontal {
        display:table;
        width:100%;
    }
    .panel.panel-horizontal > .panel-heading, .panel.panel-horizontal > .panel-body, .panel.panel-horizontal > .panel-footer {
        display:table-cell;
    }
    .panel.panel-horizontal > .panel-heading {
        /*width: 25%;*/
        border:0;
        vertical-align: middle;
    }.panel.panel-horizontal > .panel-body {
        /*width: 25%;*/
        border:0;
        vertical-align: middle;
    }
    .panel.panel-horizontal > .panel-footer{
        width: 40%;
        border:0;
        vertical-align: middle;
    }
    .panel.panel-horizontal > .panel-heading {
        border-right: 1px solid #ddd;
        border-top-right-radius: 0;
        border-bottom-left-radius: 4px;
    }
    .panel.panel-horizontal > .panel-body {
        border-right: 1px solid #ddd;
        border-top-right-radius: 0;
        border-bottom-left-radius: 4px;
    }
    .panel.panel-horizontal > .panel-footer {
        /*border-left: 1px solid #ddd;*/
        /*border-top-left-radius: 0;*/
        /*border-bottom-right-radius: 4px;*/
    }
    .panel-body.panel-color{
        color:white;
        background: #2EA3E7;
    }
    .panel-footer.panel-color{
        width: 40%;
        color:white;
        background: #2EA3E7;
    }
</style>
<div class="col-md-12">   
    <div id="wrap" class="col-md-8">
        <h1 class="text-center text-primary"><s:property value="propiedad.nombre"/></h1>
        <div class="panel panel-primary panel-horizontal">
            <div class="panel-body">
                <i class="fa fa-home fa-1x"></i>
                Semipiso      
            </div>
            <div class="panel-body">
                <i class="glyphicon glyphicon-fullscreen fa-1x"></i>
                500m<sup>2</sup>
            </div>           
            <div class="panel-body">
                <i class="glyphicon glyphicon-resize-small fa-1x"></i>
                255m<sup>2</sup>
            </div>
            <div class="panel-body">
                <i class="fa fa-bed fa-1x"></i>
                2
            </div>
            <div class="panel-footer panel-color">
                Codigo: <s:property value="propiedad.codigoPropiedad"/>
            </div>
        </div>            
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
        <div id="detalles">
            <h2 class="text-center text-info">Detalles</h2>
            <p>EDIFICIO SAN JOSE II DEPARTAMENTO DE DOS DORMITORIOS PARA 6 PERSONAS CON AIRE ACONDICIONADO EN EL ESTAR COMEDOR. COCINA TOTALMENTE EQUIPADA. CUCHETAS EN UN DORMITORIO, SOMMIER MATRIMONIAL EN EL DORMITORIO PRINCIPAL. TV CON DIRECT TV.- COCHERA EN SUBSUELO. ASCENSOR DE SUBSUELO A TERRAZA. TERRAZA CON PISCINA, ASADORES, QUINCHO CERRADO CLIMATIZADO EQUIPADO.- Comercializa Daniel Chaves Servicios Inmobiliarios En el desarrollo de ventas de departamentos la inmobiliaria Daniel Chaves tiene las mejores propuestas en Villa Carlos Paz. Amplia oferta de inmuebles, lotes, casas, dúplex y departamentos en Carlos Paz. Somos un grupo de profesionales trabajando con el objetivo principal de satisfacer de un modo integral brindando respuestas a todas y cada una de sus necesidades. Gracias por seguir prefiriéndonos entre las inmobiliarias en Carlos Paz.-</p>
        </div>
    </div>
    <div class="col-md-4">
        <div id="servicios" style="padding-top: 150px">
            <h3 class="text-center text-info">Servicios</h3>
            <div class="col-md-6">
                <s:iterator value="serviciosUno" var="servicio" status="stat">                  
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-2x" style="color: green"></i><span >   <s:property value="#servicio.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
            <div class="col-md-6">
                <s:iterator value="serviciosDos" var="servicio" >
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-2x" style="color: green"></i><span >   <s:property value="#servicio.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
        </div>
        <div id="ambientes">
            <div class="col-md-12">
                <h3 class="text-center text-info">Ambientes</h3>    
            </div>            
            <div class="col-md-6">
                <s:iterator value="serviciosUno" var="servicio" >
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-2x" style="color: green"></i><span >   <s:property value="#servicio.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
            <div class="col-md-6">
                <s:iterator value="serviciosDos" var="servicio" >
                    <div class="panel panel-default panel-horizontal">
                        <div class="panel-body">
                            <i class="fa fa-check fa-2x" style="color: green"></i><span >   <s:property value="#servicio.nombre"/></span>
                        </div>                       
                    </div>              
                </s:iterator>
            </div>
           
        </div>
        <div class="col-md-12" id="formulario-consulta" style="background: #F1F3F6; padding-bottom: 50px;">
            <h3 class="text-center" style="color: black; padding-bottom: 25px; padding-top: 40px">Contacto</h3>
            <s:form action="" validate="true" enctype="multipart/form-data" theme="bootstrap" cssClass="form-horizontal ">
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textfield name="nombre" placeholder="Nombre" cssClass="form-control" />
                </div>
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textfield name="email" placeholder="Email" cssClass="form-control" />
                </div>
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textfield name="telefono" placeholder="Teléfono" cssClass="form-control" />
                </div>
                <div class="col-md-10 col-md-offset-2 ">
                    <s:textarea name="consulta" placeholder="Mensaje" cssClass="form-control" />
                </div>
                <div class="col-md-offset-4 ">
                    <s:submit value="Enviar Consulta" cssClass="btn" style="border-color:#adb2b6;"/>
                </div>
            </s:form>
        </div>
    </div>



</div>