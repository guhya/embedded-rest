<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@	taglib uri="http://guhya.net" prefix="g" %>

<tiles:insertDefinition name="_base">
    
    <tiles:putAttribute name="body">
    
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">
					<i class="fa fa-user"></i> User List
				</h3>
				<ol class="breadcrumb">
					<li><i class="fa fa-home"></i><a href="<c:url value="/manager/dashboard.do"/>">Home</a></li>
					<li><i class="fa fa-list"></i>User List</li>
				</ol>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> Search User </header>
					<div class="panel-body">
						<form name="searchForm" class="form-inline" role="form" method="get" action="list.do">				
							<div class="form-group">
								<select name="condition" id="condition" class="form-control" required="required">
									<option value="firstName" <c:if test="${parameter.condition == 'firstName'}">selected</c:if>>First Name</option>
									<option value="username" <c:if test="${parameter.condition == 'username'}">selected</c:if>>Username</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" required="required" name="keyword" id="keyword" value="<c:out value="${parameter.keyword}" />" />
							</div>
							
							<input type="hidden" name="sortColumn" 	value="<c:out value="${parameter.sortColumn}"/>" />
							<input type="hidden" name="sortOrder" 	value="<c:out value="${parameter.sortOrder}"/>" />
							<input type="hidden" name="pageSize" 	value="<c:out value="${parameter.pageSize}"/>" />

							<button type="submit" class="btn btn-primary">Search</button>
							
							<button type="button" onclick="javascript:top.location='write.do'" class="btn btn-lg btn-success pull-right">
								<i class="fa fa-pencil">&nbsp;</i> Write
							</button>
						</form>
					</div>
				</section>
			</div>
			
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> Sorting </header>
					<div class="panel-body">
						<form name="sortForm" class="form-inline" role="form">
							<div class="form-group">
								<div class="input-group" style="max-width: 320px;">
									<span class="input-group-addon" id="sb">Sort Column</span>
									<select name="tmpSortColumn" id="tmpSortColumn" class="form-control" aria-describedby="sb">
										<option value="">Select Column</option>
										<option value="firstName" <c:if test="${parameter.sortColumn == 'firstName'}">selected</c:if>>First Name</option>
										<option value="username" <c:if test="${parameter.sortColumn == 'username'}">selected</c:if>>Username</option>
									</select>
								</div>
							</div>						
							<div class="form-group">
								<div class="input-group" style="max-width: 320px;">
									<span class="input-group-addon" id="so">Sort Order</span>
									<select name="tmpSortOrder" id="tmpSortOrder" class="form-control" aria-describedby="so">
										<option value="">Select Order</option>
										<option value="ASC" <c:if test="${parameter.sortOrder == 'ASC'}">selected</c:if>>Ascending</option>
										<option value="DESC" <c:if test="${parameter.sortOrder == 'DESC'}">selected</c:if>>Descending</option>
									</select>
								</div>
							</div>
							<button type="button" onclick="sort();" class="btn btn-primary">Go</button>
							<a href="list.do?mediaType=xls" class="btn btn-default pull-right">
								<i class="fa fa-file-excel-o">&nbsp;</i>Download <strong>Excel</strong></a>
							<a href="list.do?mediaType=json" target="_blank" style="margin-right:5px;" class="btn btn-default pull-right">
								{} Show <strong>JSON</strong></a>
							<a href="list.do?mediaType=xml" target="_blank" style="margin-right:5px;" class="btn btn-default pull-right"> 
								<i class="fa fa-code">&nbsp;</i> Show <strong>XML</strong></a>
						</form>						
					</div>
				</section>
			</div>			
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<section class="panel table-responsive">
					<table class="table table-striped table-advance table-hover">
						<tbody>
							<tr>
								<th><i class="fa fa-info-circle">&nbsp;</i> Username</th>
								<th class=""> First Name</th>
								<th>Last Name</th>
								<th class="">Roles</th>
								<th class=""><i class="fa fa-calendar-o">&nbsp;</i> Reg. Date</th>
								<th class="" style="text-align: center;"><span class="pull-right"><i class="fa fa-gears">&nbsp;</i> Action</span></th>
							</tr>
							<c:if test="${not empty user}">
								<c:forEach var="item" items="${user}" varStatus="status">
									<tr>
										<td onclick="location.href='detail.do?username=<c:out value="${item.username}"/>'">
											<a href="detail.do?username=<c:out value="${item.username}"/>&<c:out value="${parameter.queryString}"/>">
												<c:out value="${item.username}"/>
											</a>
										</td>
										<td class=""><c:out value="${item.firstName}"/></td>
										<td class=""><c:out value="${item.lastName}"/></td>
										<td class=""><c:out value="${fn:replace(item.role, ',', ', ')}"/></td>
										<td class=""><fmt:formatDate value="${item.regDate}" pattern="yyyy-MM-dd"/></td>
										<td class="">
											<div class="btn-group pull-right">
												<a href="detail.do?username=<c:out value="${item.username}"/>" class="btn btn-info"><i class="fa fa-search"></i> Detail</a>
												<a href="edit.do?username=<c:out value="${item.username}"/>" class="btn btn-warning"><i class="fa fa-edit"></i> Edit</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty user}">
								<tr>
									<td colspan="6">No Data</td>
								</tr>
							</c:if>		    
						</tbody>
					</table>
				</section>
			</div>
		</div>
    </tiles:putAttribute>
    
    <tiles:putAttribute name="js">
    	<script>
    		$(document).ready(function(){    			
    			
    		});
    	</script>
    </tiles:putAttribute>
    
</tiles:insertDefinition>
