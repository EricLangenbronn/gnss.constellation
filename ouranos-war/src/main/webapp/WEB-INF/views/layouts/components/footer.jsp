<%@taglib uri="Struts" prefix="s"%>
<!-- Footer -->

<footer class="bs-footer" role="footer">
    <div class="col-md-6">
    	${version}
    </div>
<!--     <div class="col-md-6 text-right"> -->
<!--       <a rel="start" href="#">haut de page</a> -->
<!--     </div> -->
</footer>
<style>
.tooltipColor {
    border-color: #818181;
    background-color: #2a718c;
    color:white;
}
</style>
<div>
<!--[if lt IE 9]>
  <script src="<s:url value='/media/js/jquery/jquery-1.10.2.min.js'/>"></script>
<![endif]-->
<!--[if gte IE 9]><!-->
  <script src="<s:url value='/media/js/jquery/jquery-2.1.1.min.js'/>"></script>
<!--<![endif]-->
<script src="<s:url value='/media/js/jquery/jquery.reject.js'/>"></script>
<script src="<s:url value='/media/js/jquery/jquery-ui.js'/>"></script>
<script src="<s:url value='/media/js/bootstrap/bootstrap.js'/>"></script>
<script src="<s:url value='/media/js/jquery/jquery.inputfile.js'/>"></script>
<script src="<s:url value='/media/js/jquery/jstree.min.js'/>"></script>
<script src="<s:url value='/media/js/jquery/jquery.qtip.min.js'/>"></script>
<script>
  $(function() {
    $("[data-toggle='tooltip']").qtip({
      content: {
          attr: 'data-original-title'
      },
      position: {
        my: 'top left',
        at: 'bottom left',
        adjust: {
            x: 20
        }},
      style: {
        tip: {
          corner: 'top left',
          mimic: 'center top',
          offset : 10
        },
        classes: 'tooltipColor'
      },
      show: {
        effect: function(offset) {
          $(this).slideDown(100); // "this" refers to the tooltip
        },
        delay:500
      },
      hide: {
        effect: function(offset) {
          $(this).slideDown(100); // "this" refers to the tooltip
        }
      }
      
    });
    
    $("[data-toggle='menutooltip']").qtip({
      content: {
          attr: 'data-original-title'
      },
      position: {
        my: 'top left',
        at: 'bottom left',
        adjust: {
            x: 20
        }},
      style: {
        tip: {
          corner: 'top left',
          mimic: 'center top',
          offset : 10
        },
        classes: 'tooltipColor'
      },
      show: {
        effect: function(offset) {
          $(this).slideDown(100); // "this" refers to the tooltip
        },
        delay:500
      },
      hide: {
        effect: function(offset) {
          $(this).slideDown(100); // "this" refers to the tooltip
        },
        delay:250
      }    
    });

    $('input[id="menuForm_menufichierImport"]').inputfile({
      uploadText : '<span style="color : #363636;">Importer</span>',
      removeText : '<span style="color : #363636;">Importer</span>',
      restoreText : '<span style="color : #363636;">Importer</span>',
  
      uploadButtonClass : 'btn btn-link',
      removeButtonClass : 'btn btn-link',
  
      removeName : '',
      removeValue : 1,
  
      dontRemove : true
    });
  
    $('input[id="menuForm_menufichierImport"]').on('change', function() {
      $('#menuForm').submit();
    });
  
    $('input[id="menuForm_menufichierImport"]').attr('title', window.webkitURL ? ' ' : '');
  
    $('#menuForm').find('.inputfile').attr("style", "margin-bottom: 1px; margin-top: 1px;" );
  
    /* rejet navigateur obsolète*/
    $.reject({
      reject: { all: false, msie: 8 }, // Reject all renderers for demo
      browserShow: true, // Should the browser options be shown?
      display: ['msie'], // Displays only firefox, chrome, and opera
      browserInfo: { // Settings for which browsers to display
      msie: {
        text: 'Internet Explorer',
        url: 'http://www.microsoft.com/windows/Internet-explorer/'
      }
    },
    close: false, // Prevent closing of window
      imagePath: './media/img/',
      header: 'Votre&nbsp;navigateur&nbsp;est&nbsp;obsolète',
      paragraph1: 'Cette application n\'est pas compatible avec les versions de Internet explorer inférieure à la version 8.', // Warning about closing
      paragraph2: 'Pour poursuivre votre navigation veuillez mettre à jour la version de votre navigateur en cliquant sur l\'image correspondante ci-dessous, ou vous connecter en utilisant un autre navigateur.'
    });
  }); 

  function rediriger() {
    switch ($('.form-control').find(':selected').attr('value')) {
    case "validationStructure":
      window.location.href = '<s:url action="creerTest" namespace="/"><s:param name="typeTest" value="%{\'TEST_STRUCTURE\'}"/></s:url>';
      break;
    case "validationEvolutions":
      window.location.href = '<s:url action="creerTest" namespace="/"><s:param name="typeTest" value="%{\'TEST_EVOLUTION_API\'}"/></s:url>';
      break;
    case "validationReglesMetier":
      window.location.href = '<s:url action="creerTest" namespace="/"><s:param name="typeTest" value="%{\'TEST_REGLES_METIER\'}"/></s:url>';
      break;
    }
  }
</script>
</div>