<%@taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<fieldset>
    <div class="col-md-12 text-center">
        <s:actionerror theme="bootstrap"/>
        <s:actionmessage theme="bootstrap"/>
        <s:fielderror theme="bootstrap"/>
    </div>
</fieldset>
    <p>Bienvenido <s:property value="#session.user.nombre"/> !</p>
       