var infixExpression;

$(document).ready(function() {
	$(document).on("click", '#calculateButton', function() {
		infixExpression = $("#input").val();
		calculate()
	});
});

function calculate() {
	$.ajax({
		type : 'GET',
		url : "/calculator",
		contentType : "application/json",
		dataType : "text",
		data : {
			infixExpression : decodeURIComponent(infixExpression)
		},
		success : function(result) {
			console.log(result)
			$("#result").text(result);
		},
		error : function() {
			//alertModal();
		}
	});
};

function alertModal() {
	$('#alert-modal-title').html("Please Try Again!");
	$('#alert-modal-body').html(
			"Invalid arguments inputted. Calculator accepts only 0-9 +-/*().");
	$('#alert-modal').modal('show');
}