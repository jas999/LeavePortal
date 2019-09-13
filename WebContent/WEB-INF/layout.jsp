<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.2/css/all.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<link rel="stylesheet" href="/LeaveRequest/static/style.css">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/LeaveRequest/static/layout.js"></script>

<title>Leave Portal</title>
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-sm navbar-dark">
		<i class="fas fa-bars btn sidenav-toggle"></i> <a class="navbar-brand"
			href="/LeaveRequest/portal">Frys Leave Management</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="fas fa-ellipsis-h"></i>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link text-white"
					href="/LeaveRequest/portal"><i class="far fa-user"></i> <% out.println(session.getAttribute("name"));%></a>
				</li>
				<li class="nav-item active"><a class="nav-link"
					href="/LeaveRequest/setting"><i class="fas fa-user-cog"></i>
						Settings </a></li>
				<li class="nav-item logout-item"><a class="nav-link"
					href="/LeaveRequest/logout"><i class="fas fa-sign-out-alt"></i>
						Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="sidenav">
		<div class='sidenav-link'>
			<a href="/LeaveRequest/portal" id="all-req">My Leave Requests</a> <a
				href="/LeaveRequest/comp" id="all-comp">My Comp-Off Requests</a> <a
				href="/LeaveRequest/leave" id="new-req">New Leave Request</a> <a
				href="/LeaveRequest/compoff" id="comp-req">New Comp-Off Request</a>
		</div>
	</div>
	<div style="margin-left: 240px; margin-top: 50px; padding: 40px;"
		class="main-div">