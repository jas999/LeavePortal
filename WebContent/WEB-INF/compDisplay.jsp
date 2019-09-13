<%@page import="com.backyardev.util.DatabaseQueries"%>
<%@page import=" java.util.ArrayList"%>
<%@page import="com.backyardev.util.CompoffReqObject"%>

<jsp:include page="/WEB-INF/layout.jsp"></jsp:include>

<%
  		String url = (String)request.getAttribute("javax.servlet.forward.request_uri");
		String updatedUrl = url.replace("/LeaveRequest/comp/","");
	 	ArrayList<CompoffReqObject> resultSet = DatabaseQueries.getCompLeave(updatedUrl);
      %>
<% for(int i = 0; i < resultSet.size(); i+=1) { %>
<div class="alert"
	style="display: none; width: 65%; margin-left: auto; margin-right: auto;"
	role="alert"></div>
<form class="form leave-form" method="post" action="compoff">
	<h3>Comp-Off Request</h3>
	<br>
	<div class="form-group">
		<label for="name">Name of the Employee</label> <input
			class="form-control" id="name" required type="text" readonly
			value="<%=resultSet.get(i).getName()%>" name="name"
			placeholder="Name" />
	</div>
	<div class="form-row">
		<div class="col-sm-6">
			<div class="form-group">
				<label for="ecode">Ecode</label> <input class="form-control"
					id="ecode" required type="text" readonly name="ecode"
					value="<%=resultSet.get(i).getEcode()%>" placeholder="Ecode" />
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label for="project">Project</label> <input class="form-control"
					id="project" value="<%=resultSet.get(i).getProject()%>" readonly
					required type="text" name="project" placeholder="Project" />
			</div>
		</div>
	</div>
	<div class="form-row">
		<div class="col-sm-6">
			<div class="form-group">
				<label for="tLead">Team Lead</label> <input class="form-control"
					id="tLead" required value="<%=resultSet.get(i).getTeamLead()%>"
					readonly type="text" name="tLead" placeholder="Team Lead" />
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label for="manager">Project Manager</label> <input
					class="form-control" id="manager" required
					value="<%=resultSet.get(i).getManager()%>" readonly type="text"
					name="manager" placeholder="Team Lead" />
			</div>
		</div>
	</div>
	<div class="form-row">
		<div class="col-sm-6">
			<div class="form-group">
				<label for="ticket">Ticket/SCR</label> <input class="form-control"
					id="ticket" required type="text"
					value="<%=resultSet.get(i).getTicket()%>" readonly name="ticket"
					autocomplete="off" />
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label for="comp-date">Date</label> <input
					class="form-control date-format" id="comp-date" required
					type="text" value="<%=resultSet.get(i).getCompDate()%>" readonly
					name="comp-date" value="yy-mm-dd" autocomplete="off" />
			</div>
		</div>
	</div>
	<div>
		<div class="form-group">
			<label for="night-shift">Night Shift</label> <input
				class="form-control date-format" id="comp-date" required type="text"
				value="<%=resultSet.get(i).getNightShift()%>" readonly
				name="comp-date" autocomplete="off" />
		</div>
	</div>
	<div class="form-group">
		<label for="comp-desc">Description</label>
		<textarea class="form-control" required name="comp-desc"
			placeholder="<%=resultSet.get(i).getDesc()%>" readonly rows="3"
			autocomplete="off"></textarea>
	</div>

</form>

<% } %>
<div class="modal fade" id="submitFormModal" tabindex="-1" role="dialog"
	aria-labelledby="submitFormModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="ModalTitle">Request Comp-Off?</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to request for Comp-Off?</p>
				<br>
				<div style="text-align: center; display: none;" id="progress">
					<img src="static/loading.gif" alt="loading..">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					id="modal-dismiss-btn" data-dismiss="modal">Cancel</button>
				<button id="submitFormBtn" value="Add" class="btn btn-primary">Yes</button>
			</div>
		</div>
	</div>
</div>

</div>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<script>
			$(function(){
				$('#submitFormModal').modal({ show: false});
				$( "#comp-date" ).datepicker({
					  dateFormat: "yy-mm-dd"
				});
			});
			$('.form').submit(function(s){
				s.preventDefault();
				console.log('cvla: '+$('#comp-date').val());
				$('#submitFormModal').modal('show');
				$('#submitFormBtn').click(function(){
					$('#progress').css('display', 'block');
					var form = $('.form');
					var url = form.attr("action");
					var method = form.attr("method");
					$.ajax({
						type: method,
						url: url,
						data: form.serialize(),
						success: function(data){
							if (data == "true"){
								showAlert('Post Success!', 'alert-success');
							} else if(data == "null"){
								showAlert('All Fields are compulsory!', 'alert-warning');
							}
						}
					});
				});
			});
			function showAlert(msg, type){
				$('#progress').css('display', 'none');
				$('#submitFormModal').modal('hide');
				$('.alert').addClass(type);
				$('.alert').html(msg);
				$('.alert').css('display', 'block');
				$('html, body').animate({scrollTop: 0});
				$(".alert").fadeTo(2000, 500).slideUp(500, function(){
				    $(".alert").slideUp(500);
				});
			}
		</script>
</body>
