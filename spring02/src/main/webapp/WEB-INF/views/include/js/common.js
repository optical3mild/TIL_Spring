/* views/include/js/common.js */

// 이미지 파일인지 확인
function checkImageType(fileName){
	var pattern = /jpg|gif|png|jpeg/; // /정규표현식/ , | = or
	return fileName.match(pattern);	// --> 일치하는 부분을 리턴
}

function getFileInfo(fullName) {
	var fileName, imgsrc, getLink, fileLink;
	if(checkImageType(fullName) != null){ // 이미지 파일인 경우
		imgsrc = "/spring02/upload/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // 0 ~ 11
		var end = fullName.substr(14); // 14인덱스 ~ 끝
		
		getLink = "/spring02/upload/displayFile?fileName="+front+end;
	} else {	// 이미지 파일이 아닌경우
		fileLink = fullName.substr(12);
		getLink = "/spring02/upload/displayFile?fileName="+fullName;
	}
	// uuid_fileName --> fileName만 추출
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	// json데이터로 리턴
	return {fileName: fileName, imgsrc: imgsrc, getLink: getLink, fullName: fullName};
}