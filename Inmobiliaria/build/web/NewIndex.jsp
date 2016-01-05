<s:iterator var="oportunidad" value="propiedades">
    <div id="novedad" class="col-sm-6 col-md-4">
        <div class="panel panel-primary">
            <div class="panel-body" style="border:1px solid #ddd;">
                <a  class="" href="<s:url action="ver" namespace="/">
                        <s:param name="idPropiedad"><s:property value="%{#oportunidad.idPropiedad}"/></s:param>
                    </s:url>">
                    <img class="box img-responsive" src="/Imagen?idImagen=<s:url value="%{#oportunidad.imagenDefault.idImagenPropiedad}"/>">
                </a>
                <div class="panel panel-horizontal" style="border:1px solid #ddd;">
                    <div class="panel-body">
                        <i class="fa fa-bed fa-1x"></i>
                        <s:property value="%{#oportunidad.habitacion}"/>
                    </div>
                    <div class="panel-body">
                        <i class="icon-bathrooms"></i>
                        <s:property value="%{#oportunidad.bano}"/>
                    </div>
                    <div class="panel-body panel-color">
                        Codigo: <s:property value="%{#oportunidad.codigoPropiedad}"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</s:iterator>