<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fieldset>
    <legend>
        Nueva Propiedad
    </legend>
</fieldset>
<div class=" col-md-8">   
    <ul class="col-md-6">
        <s:iterator value="propiedad.servicios" var="servicio">
            <li>
                <i class="fa fa-check"></i> <s:property value="#servicio.nombre" />
            </li>
        </s:iterator>

    </ul>

</div>