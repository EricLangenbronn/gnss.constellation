<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav id="navbar" role="navigation" class="navbar navbar-default">

  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <button data-target="#bs-example-navbar-collapse-1" data-toggle="collapse" class="navbar-toggle" type="button">
      <span class="sr-only">Toggle navigation</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
  </div>

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
    <ul class="nav navbar-nav">
      <li title=""
            data-original-title="Retour à la liste des tests"
            data-placement="top" data-toggle="menutooltip">
        <div>
            <a href="<s:url action="accueil"/>" class="navbar-brand"><i class="glyphicons gris home"></i></a>
          </div>
      </li>

      <li title=""
            data-original-title="Permet de visualiser sous forme de tableau la dernière exécution de chacun des tests. Si un ou plusieurs tests sont en cours d'exécution, alors ceux-ci sont visualisés dans la page."
            data-placement="right" data-toggle="menutooltip">
        <a href="<s:url action="listerDernieresExecutions" namespace="/"></s:url>">Dernières exécutions</a>
      </li>
      <li title=""
            data-original-title="Importer un test sous format .zip"
            data-placement="right" data-toggle="menutooltip">
        <s:form cssClass="navbar-form" id="menuForm" action="importerFichier" method="POST" namespace="/"
          enctype="multipart/form-data">
            <s:file name="menufichierImport"></s:file>
        </s:form>
      </li>
      <li title=""
            data-original-title="Créer un nouveau test"
            data-placement="right" data-toggle="menutooltip">
        <a href="#" data-toggle="modal"
        data-target="#modalNouveauTest">Nouveau test</a>
      </li>
      <c:set var="MyPageMenu" value="${param.MyPageMenu}" scope="page"/>
      <s:if test='%{#attr.MyPageMenu == "EXECUTION"}'>
        <li title=""
              data-original-title="Lancer une nouvelle exécution de ce test"
              data-placement="right" data-toggle="menutooltip">
          <a href="<s:url action="executionTestApi" namespace="/"><s:param name="id" value="id"></s:param></s:url>">Executer</a>
        </li>
        <li title=""
              data-original-title="Modifier ce test"
              data-placement="right" data-toggle="menutooltip">
          <a href="<s:url action="creerTest" namespace="/"><s:param name="id" value="id"/></s:url>">Modifer</a>
        </li>
      </s:if>
    </ul>
  </div>
</nav>  

<div class="modal fade" id="modalNouveauTest">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
        </button>
        <h4 class="modal-title">Nouveau test</h4>
      </div>
      <div class="modal-body">
        <select class="form-control">
          <option value="">Choisissez le type de test à créer</option>
          <option value="validationStructure">Validation de la
            structure</option>
          <option value="validationEvolutions">Validation des
            évolutions</option>
          <option value="validationReglesMetier">Validation des
            règles métier</option>
        </select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
        <button type="button" class="btn btn-primary" onclick="rediriger()">
          OK</button>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->