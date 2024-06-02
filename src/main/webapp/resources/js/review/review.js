document.addEventListener('DOMContentLoaded', () => {
	let allTab = document.querySelector('#all-tab');
	let myTab = document.querySelector('#my-tab');
	
	let allReview = document.querySelector('#allReview');
	let myReview = document.querySelector('#myReview');
    
    allTab.addEventListener('click', () => {
		myTab.classList.remove('active');
		allTab.classList.add('active');
        myReview.classList.remove('show', 'active');
        allReview.classList.add('show', 'active');
        
    });
    
    myTab.addEventListener('click', () => {
        allTab.classList.remove('active');
        myTab.classList.add('active');
        allReview.classList.remove('show', 'active');
        myReview.classList.add('show', 'active');
    });
});
