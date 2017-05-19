/**
 * 
 */

function submitForm(){
	 var password = document.getElementById("password");
	   //md5加密
	 document.getElementById("password").value = hex_md5(password.value);
	 document.user.submit();
}

function clearForm(){
	$('#ff').form('clear');
}

function killerror()
{
	return true;	
}

$(function() {
	
	$('#ff').form('clear');
	window.onerror=killerror;
	
	$('#username').focus();
	
	$('#ff').submit(function(){
		if($.trim($('#username').val())=='')
		{
			$('#username').css("border-color","#ff9900");
			$('#username').focus();
			return false;
		}
		else
		{
			$('#username').css("border-color","");
		}

		if($.trim($('#password').val())=='')
		{
			$('#password').css("border-color","#ff9900");
			$('#password').focus();
			return false;
		}
		else
		{
			$('#password').css("border-color","");
		}
		
		if($.trim($('#checkcode').val()).length!=4)
		{
			$('#checkcode').css("border-color","#ff9900");
			$('#checkcode').focus();
			return false;
		}
		else
		{
			$('#checkcode').css("border-color","");
		}
		return true;
	});
});


$("#verifyClick").bind('click',function(){
		var url ='${demoPath}verifyCode.html?verifyCode=' + Math.random();
		//$('#verifyCode').attr('src',url);
		document.getElementById('verifyCode').setAttribute('src', url);
	});