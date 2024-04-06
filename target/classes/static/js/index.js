
document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll('.row-checkbox').forEach(function(checkbox) {
        toggleRowColorAndSubmit(checkbox, checkbox.dataset.todoId);
        
    });
});



var audio = new Audio("js/audio/noti.mp3");
function toggleRowColorAndSubmit(checkbox, todoId) {
	
	
	const row = checkbox.closest('tr');
	if (checkbox.checked) {
		row.classList.add('selected-row');
		audio.play();
		
	} else {
		row.classList.remove('selected-row');
	}


	$.post(`/update-completed/${todoId}`, { completed: checkbox.checked })
		.done(function(data) {

			console.log('Server response:', data);
		})
		.fail(function() {

			console.error('Error updating completed status');
		});
}
