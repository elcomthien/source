$(document).ready(function() {
	$("#sidebar").remove();
	$("#content").remove();
	$("#config-alert-error").hide();

	getAllConfig();

});

function getAllConfig() {
	$.get("getconfig.elcom", function(data) {
		$("#config-ftp-host").val(data.config.server_ftp);
		$("#config-ftp-port").val(data.config.server_port);
		$("#config-ftp-username").val(data.config.serveruserftp);
		$("#config-ftp-password").val(data.config.serverpassftp);
		$("#config-ftp-ipserver").val(data.config.desc_host);
		$("#config-ftp-srcftp").val(data.config.src_file);
		$("#config-ftp-srcserver").val(data.config.local_file);
	});
}

$(function() {
	$("#config-ftp-btnreset").click(function() {
		$("#config-ftp-host").val("");
		$("#config-ftp-port").val("");
		$("#config-ftp-username").val("");
		$("#config-ftp-password").val("");
		$("#config-ftp-ipserver").val("");
		$("#config-ftp-srcftp").val("");
		$("#config-ftp-srcserver").val("");
		$("#config-alert-error").hide();
	});
});
$(function() {
	$("#config-ftp-btnconfig").click(
			function() {
				$("#config-alert-error").hide();
				var host = $("#config-ftp-host").val();
				var port = $("#config-ftp-port").val();
				var username = $("#config-ftp-username").val();
				var password = $("#config-ftp-password").val();
				var ipserver = $("#config-ftp-ipserver").val();
				var srcfile = $("#config-ftp-srcftp").val();
				var srclocal = $("#config-ftp-srcserver").val();
				if (host == "") {
					$("#config-text-error").html(
							"Host FTP server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				if (port == "") {
					$("#config-text-error").html(
							"Port FTP server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				if (username == "") {
					$("#config-text-error").html(
							"Username FTP server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				if (password == "") {
					$("#config-text-error").html(
							"Password FTP server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				if (ipserver == "") {
					$("#config-text-error").html(
							"Ip server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				if (srcfile == "") {
					$("#config-text-error").html(
							"Src file FTP server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				if (srclocal == "") {
					$("#config-text-error").html(
							"Src file server không được để trống!");
					$("#config-alert-error").show();
					return false;
				}
				var datajson = {
					"host" : host,
					"port" : port,
					"username" : username,
					"password" : password,
					"ipserver" : ipserver,
					"srcfile" : srcfile,
					"srclocal" : srclocal
				};
				var jdata = JSON.stringify(datajson);
				$.post("updateconfig.elcom", {
					data : jdata
				}, function(r) {
					if (r.data == "true") {
						jSuccess("Success", {
							HorizontalPosition : 'center',
							VerticalPosition : 'top'
						});
						getAllConfig();
					} else {
						jError("Error", {
							HorizontalPosition : 'center',
							VerticalPosition : 'top'
						});
					}
				}).error(function() {
					jError("Error", {
						HorizontalPosition : 'center',
						VerticalPosition : 'top'
					});
				});

			});

});