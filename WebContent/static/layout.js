$(function(){
	if (window.matchMedia("(max-width: 600px)").matches) {
		$('.sidenav').css('width', 0);
		$('.main-div').css('margin-left', 0);
	} else {
		$('.sidenav').css('width', '240px');
		$('.main-div').css('margin-left', '240px');
	}

	$('.sidenav-toggle').click(function(){
		var toggleWidth;
		if (window.matchMedia("(max-width: 600px)").matches){
			toggleWidth = $(".sidenav").width() > 240 ? "0%" : "100%";
			$('.sidenav').animate({ width: toggleWidth }, 150);
		}else{
			toggleWidth = $(".sidenav").width() == 240 ? "0px" : "240px";
			$('.sidenav').animate({ width: toggleWidth }, 150);
			$('.main-div').animate({ marginLeft: toggleWidth}, 150);

		}
	});
});