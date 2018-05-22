$(function() {
	if($("#messagetemp").val() != ""){
		alert($("#messagetemp").val());
	}
	
});

$(function() {
	$("#btnlogin").click(function() {
		if($("#username").val()=="" || $("#password").val()==""){
			$("#spanerror").html("Username and password is invalid!");
			return false;
		}
//		else{
//			$.post("CheckIn",{user:$("#username").val(), password:$("#password").val()});
//		}
	});
});