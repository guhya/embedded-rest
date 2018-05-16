<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- Alert -->
<div class="modal vertical-alignment-helper" id="myAlert" tabindex="-1" role="dialog"></div>
<div class="modal vertical-alignment-helper" id="myLoading" tabindex="-1" role="dialog"></div>

<!-- javascripts -->
<script src="<c:url value="/resources/manager/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/manager/js/jquery-ui-1.10.4.min.js"/>"></script>
<script src="<c:url value="/resources/manager/js/jquery-ui-1.9.2.custom.min.js"/>"></script>
<script src="<c:url value="/resources/manager/js/jquery.lazyload.min.js"/>"></script>

<!-- bootstrap -->
<script src="<c:url value="/resources/manager/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/manager/js/bootstrap-datepicker.js"/>"></script>	

<script src="<c:url value="/resources/manager/js/scripts.js"/>"></script>
<script src="<c:url value="/resources/manager/js/jquery.autosize.min.js"/>"></script>
<script src="<c:url value="/resources/manager/js/jquery.placeholder.min.js"/>"></script>

<script>
	var showLoading = function(){
		var $loading = $(myAlert);
		$loading.find(".modal-header>button").remove();
		$loading.find(".modal-title").html("Processing..");
		$loading.find(".modal-footer").remove();
		$loading.find(".modal-body").html('<img src="<c:url value="/resources/manager/images/ajaxLoader.gif"/>" style="margin: 0 auto;display: block;"/>');
		$("#myLoading").html($loading);

		$("#myLoading").modal("show");
	};

	var hideLoading = function(){
		$("#myLoading").modal("hide");
	};

	var myAlert = '' 
		+'<div class="modal-dialog vertical-align-center">'
			+'<div class="modal-content">'
				+'<div class="modal-header">'
					+'<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'
						+'<h4 class="modal-title" id="alertTitle"></h4>'
				+'</div>'
				+'<div class="modal-body">'
					+'<p id="alertMessage"></p>'
				+'</div>'
				+'<div class="modal-footer">'
					+'<button type="button" class="btn btn-default" id="noAction" data-dismiss="modal">No</button>'
					+'<button type="button" class="btn btn-primary" id="alertAction">Yes</button>'
				+'</div>'
			+'</div>'
		+'</div>';

	var showAlert = function(title, message, action, ret){
		$("#myAlert").html(myAlert);
		
		if(action){
			$("#alertAction").attr("onclick", "$('#myAlert').modal('hide');"+action);
			if(ret){
				$("#noAction").remove();
				$("#alertAction").attr("class", "btn btn-primary").html("OK");				
			}
		}else{
			$("#alertAction").remove();
			$("#noAction").attr("class", "btn btn-primary").html("OK");
		}
		
		$("#alertTitle").html(title);
		$("#alertMessage").html(message);

		$("#myAlert").modal("show");
	};

	$(document).ready(function(){
		$("img.lazy").lazyload({
		    effect : "fadeIn"			
		});
		
		$("form[name=searchForm], form[name=sortForm]").find("select:not(#condition), input").each(function(){
			if($(this).val() != ""){
				$(this).css("background-color", "#394A59");
				$(this).css("color", "#FFF");
			}else{
				$(this).css("background-color", "#FFF");					
				$(this).css("color", "#797979");
			}		
		});
	});
	
	var sort = function(){
		var tso = document.sortForm.tmpSortOrder.value;
		var tsc = document.sortForm.tmpSortColumn.value;
		if(document.sortForm.tmpLanguage) var lg 	= document.sortForm.tmpLanguage.value;
		
		if(tsc != "" && tso == ""){
			showAlert("Alert", "Please select sort order.", "document.sortForm['tmpSortOrder'].focus()", true);
			return;
		}
		if(tso !== "" && tsc == ""){
			showAlert("Alert", "Please select sort column.", "document.sortForm['tmpSortColumn'].focus()", true);
			return;
		}
		
		var frm = document.searchForm; 
		frm.sortColumn.value 	= tsc;  
		frm.sortOrder.value 	= tso;  
		if(document.sortForm.tmpLanguage) frm.language.value 		= lg;  
		frm.submit();    			
	};
	
	var showRows = function(){
		var ps = document.getElementById("tmpPageSize").value;
		
		var frm = document.searchForm; 
		frm.pageSize.value = ps;  
		frm.submit();    			
	}; 		
</script>
