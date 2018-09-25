var infixNotation;

$(document).ready(function() {
	$(document).on("click", '#convertButton', function() {
		infixNotation = $("#input").val();
		convert()
	});
});

function convert() {
	$.ajax({
		type : 'GET',
		url : "/calculator/convert",
		contentType : "application/json",
		dataType : "text",
		data : {
			infixNotation : infixNotation
		},
		success : function(result) {
			console.log(result)
			$("#result").text(result);
		},
		error : function() {
			console.log("ERROR")
		}
	});
};