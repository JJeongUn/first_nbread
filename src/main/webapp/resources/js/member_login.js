/**
 * 
 */

window.onload = function() {
	
// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 쿠키값 없으면 공백.
var username = getCookie("username");
document.getElementById("ID").value = username;

// ID가 있는경우 아이디 저장 체크박스 체크
if(document.getElementById("ID").value != ""){
	document.getElementById("checkId").checked = true;
}

// 아이디 저장하기 체크박스 onchange
var checkId = document.getElementById("checkId");

checkId.onchange = function (event) {
    if(checkId.checked){ //checked true
        var username = document.getElementById("ID").value;
        setCookie("username", username, 30); // 30일 동안 쿠키 보관
    }else{ //checked false
    	deleteCookie("username");
    }
};

// 아이디 저장하기가  눌린상태에서, ID를 입력한 경우
var idInput = document.getElementById("ID");

idInput.addEventListener("keyup", function(e) {
	if(checkId.checked){ //checked true
    	var username = document.getElementById("ID").value;
        setCookie("username", username, 30); // 30일 동안 쿠키 보관
    }
 })
}