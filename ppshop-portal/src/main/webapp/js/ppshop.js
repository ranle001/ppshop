var TT = PPSHOP = {
	checkLogin : function(){
		var _ticket = $.cookie("PP_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8085/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				alert(data);
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到胖胖！<a href='' onclick='logOut(\""+_ticket+"\")' class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

function logOut(ticket){
	$.ajax({
		url : "http://localhost:8085/user/logOut/" + ticket,
		dataType : "jsonp",
		type : "GET",
		success : function(data){
			if(data.status == 200){
				location.href = "http://localhost:8085/page/login";
			}
		}
	});
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});