/**
 * 
 */

var g_slideIndex = 1;

function plusSlides(n) {
	showSlides(g_slideIndex += n);
}

function currentSlide(n) {
	showSlides(g_slideIndex = n);
}

function showSlides(n) {
	var i;
	var slides = $('.mySlides');
	var dots = $('.dot');
	
	if (n > slides.length) {
		g_slideIndex = 1
	}
	
	if (n < 1) {
		g_slideIndex = slides.length
	}
	
	for (i = 0; i < slides.length; i++) {
		$(slides[i]).css("display", "none");
	}
  
	for (i = 0; i < dots.length; i++) {
		$(dots[i]).removeClass("image-active");
	}
  
	$(slides[g_slideIndex - 1]).css("display", "block");
	$(dots[g_slideIndex - 1]).addClass("image-active");
}