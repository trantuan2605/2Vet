function goCat1st(element) {
	var contextRoot = $('#contextRoot').val();
	var className = element.classList[1];
	var groupId = className.split('-')[1];
	var groupName = $(element.parentNode).find('p').text();
	var url = contextRoot.concat('/widget');
	window.location.href = url + '?groupId='+ groupId + '&groupName='+groupName;
}
