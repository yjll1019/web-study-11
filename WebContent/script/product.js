//상품 등록 시 사용자가 입력한 정보의 유효성을 체크하기 위한 자바스크립트 파일.
function productCheck(){
	if(document.frm.name.value.length==0){
		alert("상품명을 써주세요.");
		frm.name.focus(); //focus() : 커서를 해당 폼에 위치하도록 하는 함수. >> 상품명 입력하도록 커서를 상품명 입력 폼에 가져다 놓는다!
		return false;
	}
	if(document.frm.price.value.length==0){
		alert("가격을 써주세요.");
		frm.price.focus();
		return false;
	}
	if(isNaN(document.frm.price.value)){
		alert("숫자를 입력해야합니다.");
		frm.price.focus();
		return false;
	}
	return true;
}