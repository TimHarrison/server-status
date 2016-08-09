// Empty JS for your own code to be here

$(document).ready(function() {
    console.log("Page loaded.");
    get_status("Pokemon%20Go");
});

$("#up").click(function() {
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	
	var data = {};
	data[csrfParameter] = csrfToken;
	data["game"] = "Pokemon Go";
	data["status"] = "UP";
	$.ajax({
		url: "/create_status",
		type: "POST",
		data: data,
	}).done(function() {
		$("#success-message").fadeTo(2000, 500).slideUp(500, function(){
		    $("#success-message").hide();
		});
		get_status("Pokemon%20Go");
	});
});

$("#down").click(function() {
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	
	var data = {};
	data[csrfParameter] = csrfToken;
	data["game"] = "Pokemon Go";
	data["status"] = "DOWN";
	$.ajax({
		url: "/create_status",
		type: "POST",
		data: data,
	}).done(function() {
		$("#success-message").fadeTo(2000, 500).slideUp(500, function(){
		    $("#success-message").hide();
		});
		get_status("Pokemon%20Go");
	});
});

function get_status(game) {
	$.getJSON("/get_status?game=" + game, function(data) {
		console.log(data.percentageUp);
		$(".progress-bar").width(data.percentageUp + "%");
		$(".progress-bar").text(data.percentageUp + "%");
	});
}

$("#learn-more").click(function() {
	$("#modal-container-learn-more").modal({
		show: 'true'
	});
})

