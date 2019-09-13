$('.btn-reject').click(function(s) {

    var url = "ActionLeaveServlet";
    var method = "post";
    var id = $(this).attr("id")
    console.log(id);

    $.ajax({
        type: method,
        url: url,
        data: {
            'id': id,
            'action': 'reject'
        },
        success: function(data) {
            if (data == "true") {
                window.location.replace('portal');
            } else {
                alert('Error!');
            }
        }
    });
});

$('.btn-check').click(function(s) {

    var url = "ActionLeaveServlet";
    var method = "post";
    var id = $(this).attr("id")
    console.log(id);

    $.ajax({
        type: method,
        url: url,
        data: {
            'id': id,
            'action': 'approve'
        },
        success: function(data) {
            if (data == "true") {
                window.location.replace('portal');
            } else {
                alert('Error!');
            }
        }
    });
});

function sort(){
	 $('#data-table').DataTable({
	 		destroy: true,
	    	"ordering": true
	    },
	    {
	        "pagingType": "scrolling"
	    });
	 $(".fa-sort").hide()
}

$('.targetRow').click(function() {
		var url = "leave/"+$(this).attr("id").trim()
		console.log(url);
		window.location.replace(url);
});

