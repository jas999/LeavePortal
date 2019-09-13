<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<title>Reset Password</title>
</head>
<body>
	<%@ include file="layout.jsp"%>
	<!-- Navbar Component -->

	<div class="container"
		style="min-width: 300px; max-width: 500px; width: 60%; margin-left: auto; margin-right: auto; margin-bottom: 4%; margin-top: 4%; background: #fff;">
		<div class="row">
			<div class="col-sm-12">
				<form class="form-horizontal form-pass" method="POST"
					action="setting">
					<h4>${name}</h4>
					<label class="control-label"><strong>Update
							Password:</strong></label>
					<div class="form-group">
						<input class="form-control" placeholder="Password"
							autocomplete="false" required type="password" name="setpass"
							id="setpass" />
					</div>
					<div class="form-group">
						<div>
							<input class="form-control" placeholder="Confirm Password"
								autocomplete="off" required type="password" id="confpass"
								name="confpass" />
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
		</div>
	</div>
	<script>
			$('.form-pass').submit(function(e){
				e.preventDefault();
				
				var form = $(this);
	      		var url = form.attr('action');
	      		var method = form.attr('method');
	      		$.ajax({
	       		    type: method,
	       		    url: url,
	       		    data: form.serialize(),
	       		    success: function(s) {
	       		        if(s == 'true'){
	       		        	$('#setpass').val('');
	       		        	$('#confpass').val('');
	       		        	alert("Password Updated Successfully!");
	       		        } else if (s == "pass_no_match"){
	   						alert("Passwords do not match!");
	       		        } else if (s == "weak_pass"){
	       		        	alert("Strong Password required!");
	       		        } else {
	       		        	alert('Password update error!');
	       		        }
	       		    }
	       		});
			});
		</script>
</body>
</html>