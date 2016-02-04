$(document).ready(function(){

	//jQuery to collapse the navbar on scroll
	$(window).scroll(function() {
	    if ($(".navbar").offset().top > 50) {
	        $(".navbar-fixed-top").addClass("top-nav-collapse");
	        $(".brand-logo img").removeClass("hidden");
	    } else {
	        $(".navbar-fixed-top").removeClass("top-nav-collapse");
	        $(".brand-logo img").addClass("hidden");
	    }
	});

	//jQuery for page scrolling feature - requires jQuery Easing plugin
	$(function() {
		$('a[href*="#"]:not([href="#"])').click(function() {
		    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
		      var target = $(this.hash);
		      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
		      if (target.length) {
		        $('html, body').animate({
		          scrollTop: target.offset().top
		        }, 1000);
		        return false;
		      }
		    }
		  });
	});
});