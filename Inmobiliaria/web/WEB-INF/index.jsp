<%@taglib uri="/struts-tags" prefix="s"%>
<style type="text/css">
    .carousel-caption a{
        color: white;
    }
    .carousel-caption h4{
        display: block;
        background-color: rgba(0,0,0,0.6);
        position: relative;
        padding: 15px;
        width: 100%;
        margin: 0;
    }
</style>
<s:if test="destacados != null && !destacados.isEmpty()">
    <div class="container">
        <h2 class="page-header text-center">Propiedades destacadas</h2>
        <div class="row">
            <div class="col-xs-12">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <!--            <ol class="carousel-indicators">
                    <s:iterator value="destacados" var="destacado" status="stat">
                        <s:if test="#stat.index == 0">
                            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        </s:if>
                        <s:else>
                        <li data-target="#myCarousel" data-slide-to="<s:property value="#stat.index"/>"></li>
                        </s:else>
                    </s:iterator>
            </ol>-->
                    <!-- Wrapper for slides -->

                    <div class="carousel-inner" role="listbox" >
                        <s:iterator value="destacados" var="destacado" status="stat">
                            <s:if test="#stat.index == 0">
                                <div class="item active">
                                    <a href="<s:url action="ver" namespace="/">
                                           <s:param name="idPropiedad"><s:property value="%{#destacado.propiedad.idPropiedad}"/></s:param>
                                       </s:url>">
                                        <img src="/Imagen?idImagenDestacado=<s:url value="%{#destacado.imagen.idImagen}"/>">
                                    </a>
                                    <div class="carousel-caption" id="titulo-imagen">
                                        <h4><a href="<s:url action="ver" namespace="/">
                                                   <s:param name="idPropiedad"><s:property value="%{#destacado.propiedad.idPropiedad}"/></s:param>
                                               </s:url>"><s:property value="%{#destacado.nombre}"/></a></h4>
                                    </div>     
                                </div>     
                            </s:if>
                            <s:else>
                                <div class="item">
                                    <a href="<s:url action="ver" namespace="/">
                                           <s:param name="idPropiedad"><s:property value="%{#destacado.propiedad.idPropiedad}"/></s:param>
                                       </s:url>">
                                        <img src="/Imagen?idImagenDestacado=<s:url value="%{#destacado.imagen.idImagen}"/>">
                                    </a>
                                    <div class="carousel-caption" id="titulo-imagen">
                                        <h4><a href="<s:url action="ver" namespace="/">
                                                   <s:param name="idPropiedad"><s:property value="%{#destacado.propiedad.idPropiedad}"/></s:param>
                                               </s:url>"><s:property value="%{#destacado.nombre}"/></a></h4>
                                    </div>  
                                </div>     
                            </s:else>
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
        </div>
    </div>
</s:if>
<s:if test="propiedades != null && !propiedades.isEmpty()">
    <div class="container">
        <h2 class="page-header text-center">Oportunidades</h2>
        <s:iterator var="oportunidad" value="propiedades" status="stat">
            <s:if test="#stat.index % 3 == 0">
                <div class="row form-group">
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <div class="panel panel-default">
                            <div class="panel-image hide-panel-body">
                                <a  class="" href="<s:url action="ver" namespace="/">
                                        <s:param name="idPropiedad"><s:property value="%{#oportunidad.idPropiedad}"/></s:param>
                                    </s:url>">
                                    <img class="box panel-image-preview" src="/Imagen?idImagen=<s:url value="%{#oportunidad.imagenDefault.idImagenPropiedad}"/>">
                                </a>
                            </div>
                            <div class="panel-footer text-center">
                                <i class="fa fa-bed fa-1x"></i>
                                <s:property value="%{#oportunidad.habitacion}"/>
                                <span class="">|</span>
                                <i class="icon-bathrooms"></i>
                                <s:property value="%{#oportunidad.bano}"/>
                                <span class="">|</span>
                                Codigo: <s:property value="%{#oportunidad.codigoPropiedad}"/>
                            </div>
                        </div>
                    </div>
                </s:if>
                <s:else>
                    <s:if test="#stat.index % 3 == 1">
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-image hide-panel-body">
                                    <a  class="" href="<s:url action="ver" namespace="/">
                                            <s:param name="idPropiedad"><s:property value="%{#oportunidad.idPropiedad}"/></s:param>
                                        </s:url>">
                                        <img class="box panel-image-preview" src="/Imagen?idImagen=<s:url value="%{#oportunidad.imagenDefault.idImagenPropiedad}"/>">
                                    </a>
                                </div>
                                <div class="panel-footer text-center">
                                    <i class="fa fa-bed fa-1x"></i>
                                    <s:property value="%{#oportunidad.habitacion}"/>
                                    <span class="">|</span>
                                    <i class="icon-bathrooms"></i>
                                    <s:property value="%{#oportunidad.bano}"/>
                                    <span class="">|</span>
                                    Codigo: <s:property value="%{#oportunidad.codigoPropiedad}"/>
                                </div>
                            </div>
                        </div>
                    </s:if>
                    <s:else>
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <div class="panel panel-default">
                                <div class="panel-image hide-panel-body">
                                    <a  class="" href="<s:url action="ver" namespace="/">
                                            <s:param name="idPropiedad"><s:property value="%{#oportunidad.idPropiedad}"/></s:param>
                                        </s:url>">
                                        <img class="box panel-image-preview" src="/Imagen?idImagen=<s:url value="%{#oportunidad.imagenDefault.idImagenPropiedad}"/>">
                                    </a>
                                </div>
                                <div class="panel-footer text-center">
                                    <i class="fa fa-bed fa-1x"></i>
                                    <s:property value="%{#oportunidad.habitacion}"/>
                                    <span class="">|</span>
                                    <i class="icon-bathrooms"></i>
                                    <s:property value="%{#oportunidad.bano}"/>
                                    <span class="">|</span>
                                    Codigo: <s:property value="%{#oportunidad.codigoPropiedad}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:else>
            </s:else>
            <s:if test="#stat.last == true && (#stat.index % 3 == 0 || #stat.index % 3 == 1)">
            </div>
        </s:if>
    </s:iterator>
</div>
</s:if>
