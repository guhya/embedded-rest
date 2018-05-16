<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@	taglib uri="http://guhya.net" prefix="g" %>

<tiles:insertDefinition name="_base">
    
    <tiles:putAttribute name="body"> 
		<form name="userForm" method="post" id="userForm">
			<input type="hidden" name="username"	value="<c:out value="${user.username}"/>"/>
		</form>
				
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-user"></i> User
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="<c:url value="/manager/dashboard.do"/>">Home</a></li>
					<li><i class="fa fa-info-circle"></i>User Information</li>
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
						<div class="form form-horizontal">
							<div class="form-group row">
								<label for="username" class="control-label col-lg-2">Username <span class="required">*</span></label>
								<div class="col-lg-4">
									<h4 style="font-weight:bold"><c:out value="${user.username}"/></h4>
								</div>
							</div>
							
							<div class="form-group row">
								<label for="firstName" class="control-label col-lg-2">First Name</label>
								<div class="col-lg-4">
									<p><c:out value="${user.firstName}"/></p>
								</div>
								<label for="lastName" class="control-label col-lg-2">Last Name</label>
								<div class="col-lg-4">
									<p><c:out value="${user.lastName}"/></p>								
								</div>
							</div>
							
							<div class="form-group row">
								<label for="email" class="control-label col-lg-2">Email</label>
								<div class="col-lg-10">
									<p><c:out value="${user.email}"/></p>
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
									<button class="btn btn-primary" type="button" onclick="top.location = 'list.do'">List</button>
									<button class="btn btn-success" type="button" onclick="top.location = 'edit.do'">Edit</button>
									<button class="btn btn-danger" type="button" onclick="deleteItem();">Delete User</button>
								</div>
							</div>
								
						</div>
		
					</div>
				</section>
			</div>
		</div>
    </tiles:putAttribute>
    
    <tiles:putAttribute name="js">
		<script>
			var deleteItem = function(){
				showAlert("Confirm item deletion", "Are you sure you want to delete this user?", "deleteProc();");
			}
			var deleteProc = function(){
				var frm = document.userForm;
			
				frm.action			= "delete.do";
				frm.submit();
			}	
		</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
