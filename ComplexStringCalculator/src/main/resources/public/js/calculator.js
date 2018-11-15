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
			infixExpression : infixExpression
		},
		success : function(result) {
            console.log(infixExpression)
			console.log(result)
			$("#result").text(result);
		}
	});
};