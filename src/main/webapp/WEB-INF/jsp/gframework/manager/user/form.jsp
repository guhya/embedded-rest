<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib uri="http://guhya.net" prefix="g" %>

<tiles:insertDefinition name="_base">
    
    <tiles:putAttribute name="body"> 
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-user"></i> User Management
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="<c:url value="/manager/dashboard.do"/>">Home</a></li>
					<li><i class="fa fa-edit"></i>User Management</li>
				</ol>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">
		            	User Information
					</header>		
					<div class="panel-body">
						<h1></h1>
						<div class="form">
							<form class="form-validate form-horizontal" id="userForm" name="userForm" method="post" action="?">
								<c:if test="${action == 'edit'}">
									<input type="hidden" name="seq"					value="<c:out value="${user.seq}"/>"/>
								</c:if>
								
								<div class="form-group row">
									<label for="username" class="control-label col-lg-2">Username <span class="required">*</span></label>
									<div class="col-lg-4">
									<c:if test="${action == 'write'}">
										<input class="form-control" placeholder="user@mail.com" id="username" name="username" maxlength="200" type="text" 
										required="required" value="<c:out value="${user.username}"/>">
										<p class="help-block"></p>
									</c:if>
									<c:if test="${action == 'edit'}">
										<h4 style="font-weight:bold"><c:out value="${user.username}"/></h4>
										<input type="hidden" name="username"	value="<c:out value="${user.username}"/>"/>
									</c:if>
									</div>
								</div>
								
								<c:if test="${action == 'write'}">
								<div class="form-group row">
									<label for="password" class="control-label col-lg-2">Password <span class="required">*</span></label>
									<div class="col-lg-4">
										<input class="form-control" id="password" name="password" maxlength="200" type="password" required="required">
										<p class="help-block"></p>
									</div>
									<label for="rePassword" class="control-label col-lg-2">Re-Password <span class="required">*</span></label>
									<div class="col-lg-4">
										<input class="form-control" id="rePassword" name="rePassword" maxlength="200" type="password" required="required">
										<p class="help-block"></p>
									</div>
								</div>
								</c:if>
								
								<div class="form-group row">
									<label for="firstName" class="control-label col-lg-2">First Name</label>
									<div class="col-lg-4">
										<input class="form-control" id="firstName" name="firstName" maxlength="200" type="text" value="<c:out value="${user.firstName}"/>">
										<p class="help-block"></p>
									</div>
									<label for="lastName" class="control-label col-lg-2">Last Name</label>
									<div class="col-lg-4">
										<input class="form-control" id="lastName" name="lastName" maxlength="200" type="text" value="<c:out value="${user.lastName}"/>">
										<p class="help-block"></p>
									</div>
								</div>
								<div class="form-group row">
									<label for="email" class="control-label col-lg-2">Roles</label>
									<div class="col-lg-10">
										<p><c:out value="${fn:replace(user.role, ',', ', ')}"/></p>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-lg-offset-2 col-lg-10">
										<input type="submit" name="submitHandler" style="display:none"/>
										<button class="btn btn-primary" type="button" onclick="saveProc();">Save</button>
										<button class="btn btn-default" type="button" onclick="top.location = 'list.do'">Cancel</button>
									</div>
								</div>
								
							</form>
						</div>
		
					</div>
				</section>
			</div>
		</div>
    </tiles:putAttribute>
    
    <tiles:putAttribute name="js">
		<script>	
			var saveProc = function(){
				var frm = document.userForm;
				
				<c:if test="${action == 'write'}">
				if(frm.password.value !== frm.rePassword.value){
					showAlert("Alert", "Re-Password mismatch.", "document.userForm.rePassword.focus()", true);
					return;
				}
				
				if (frm.username.value !== "" && !validateEmail(frm.username.value)){
					showAlert("Alert", "Incorrect email format.", "document.userForm.username.focus()", true);
					return;
				}
				</c:if>

				frm.submitHandler.click();
			}	
		
			var validateEmail = function(email) {
				var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

				return re.test(email);
			}
			
			$(document).ready(function(){
				<c:forEach var="item" items="${errors}" varStatus="status">
					$("#<c:out value="${item.key}"/>").parent().addClass("has-error");
					$("#<c:out value="${item.key}"/>").parent().find(".help-block").html("<c:out value="${item.value}"/>");
				</c:forEach>
			});	
		</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
