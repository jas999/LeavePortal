//Function to submit form
$('.form').submit(function(s){
	s.preventDefault();
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
					window.location.replace('portal');
				} else if (data == "null") {
					showAlert('All fields are compulsory!','alert-warning');
				} else if (data == 'mail_not_sent') {
					showAlert('There was a problem sending a Leave Request Mail!','alert-warning');
				}
			}
		});
	});
});
  
//alert method
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
		
//Methods to count leave days
function GetDays(){
	var dropdt = new Date(document.getElementById("leave-end").value);
	var pickdt = new Date(document.getElementById("leave-start").value);
	return parseInt((dropdt - pickdt) / (24 * 3600 * 1000)+1);
       
}

function cal(){
    if(document.getElementById("leave-end")){
        var days = GetDays();
        document.getElementById("number-days").value=GetDays();
    }  
}
//Method for calander range
$( "#leave-start" ).datepicker({
	  dateFormat: "yy-mm-dd"
}); 
$( "#leave-end" ).datepicker({
	  dateFormat: "yy-mm-dd"
}); 
$( function() {
	$('#submitFormModal').modal('hide');
    var dateFormat =  "yy-mm-dd",
    from = $( "#leave-start" )
    .datepicker({
    	defaultDate: "+1w",
    	changeMonth: true,
    	numberOfMonths: 1
    })
    .on( "change", function() {
    	to.datepicker( "option", "minDate", getDate( this ) );
    }),
    to = $( "#leave-end" ).datepicker({
    	defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1
    })
    .on( "change", function() {
    	from.datepicker( "option", "maxDate", getDate( this ) );
    });
	 
    function getDate( element ) {
    	var date;
    	try {
			date = $.datepicker.parseDate( dateFormat, element.value );
    	} catch( error ) {
    		date = null;
    	}
	 
    	return date;
    }
});

// Client side validation
$.validate();
