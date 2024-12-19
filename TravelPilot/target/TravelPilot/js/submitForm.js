function submitForm(actionUrl) {
	const form = document.getElementById("editForm");
	form.action = actionUrl; // 폼의 action 설정
	form.submit(); // 폼 제출
}