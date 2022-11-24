<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="generales/importsGenerales.jsp" %>

<div class="w3-container">
  <table class="w3-table w3-striped w3-centered">
    <tr class="w3-teal">
      <th>Número de envío</th>
      <th>Nombre de cliente</th>
      <th>Dirección de envío</th>
      <th>Costo de envío</th>
      <th>Fecha de salida</th>
      <th>Fecha de entrega</th>
      <th>Estado actual de envío</th>
      <th>Acciones</th>
    </tr>
    <c:forEach var="envio" items="${envios}">
      <tr>
        <td class="w3-cell-middle">${envio.id}</td>
        <td>${envio.cliente.nombre}</td>
        <td>${envio.direccionEnvio.nombreCompleto}</td>
        <td><f:formatNumber type="number" value="${envio.costo}" maxFractionDigits="2"/></td>
        <td>${envio.fechaSalida.toString()}</td>
        <td>${envio.fechaLlegada.toString()}</td>
        <td>${envio.estadoEnvio.toString()}</td>
        <td>
          <a class="w3-block w3-button w3-teal w3-" href="${pagePath}/envios/siguienteEtapa?idEnvio=${envio.id}">Siguiente etapa</a>
          <a class="w3-block w3-button w3-teal" href="${pagePath}/envios/anteriorEtapa?idEnvio=${envio.id}">Anterior etapa</a>
          <a class="w3-block w3-button w3-teal" href="${pagePath}/envios/devolver?idEnvio=${envio.id}">Devolver</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<%@ include file="generales/footer.jsp" %>