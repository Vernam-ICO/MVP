(function ($) {
    "use strict";
    
    $(document).ready(function() {

        /*=================================*/
            /* mobilemenu-trigger */
        /*=================================*/

        var windowWidth = $(window).width();
 
        if (windowWidth <= 767) {
            $('#main-navigation li.menu-item-has-children').prepend('<span class="fa fa-angle-down"></span>');
            $('#main-navigation li.menu-item-has-children ul').hide();
            $('#main-navigation li.menu-item-has-children span.fa-angle-down').on('click', function(){
                $(this).siblings('#main-navigation li.menu-item-has-children ul').slideToggle(500);
            });
        };


        /*====================================
        // menu-fix
        ======================================*/

        jQuery(window).on('scroll', function() {
            if ($(this).scrollTop() > 100) {
                $('.menu').addClass("affix");
            } else {
                $('.menu').removeClass("affix");
            }
        });

        
        /*====================================
        // course-carousel
        ======================================*/


        $('.course-carousel.owl-carousel').owlCarousel({
            loop: true,
            margin: 30,
            nav: true,
            dots: true,
            autoplay: true,
            autoplayHoverPause: true,
            responsiveClass:true,
            navText : [
              "<i class='fa fa-long-arrow-left'></i>",
              "<i class='fa fa-long-arrow-right'></i>"
              ],
            responsive: {
                0:{
                    items:1
                },
                480:{
                    items:1
                },
                600:{
                    items:2
                },
                1000:{
                    items:2
                },
                1170:{
                    items:3
                }
            }
        });

        /*=================================*/
            /* news-carousel */
        /*=================================*/ 

        $('.news-carousel.owl-carousel').owlCarousel({
            loop: true,
            margin: 30,
            nav: true,
            dots: true,
            autoplay: true,
            autoplayHoverPause: true,
            responsiveClass:true,
            navText : [
              "<i class='fa fa-long-arrow-left'></i>",
              "<i class='fa fa-long-arrow-right'></i>"
              ],
            responsive: {
                0:{
                    items:1
                },
                480:{
                    items:1
                },
                600:{
                    items:2
                },
                1000:{
                    items:2
                },
                1170:{
                    items:3
                }
            }
        }); 

        /*=================================*/
            /* team-carousel */
        /*=================================*/

        $('.team-carousel.owl-carousel').owlCarousel({
            loop: true,
            margin: 30,
            nav: true,
            dots: true,
            autoplay: true,
            autoplayHoverPause: true,
            responsiveClass:true,
            navText : [
              "<i class='fa fa-long-arrow-left'></i>",
              "<i class='fa fa-long-arrow-right'></i>"
              ],
            responsive: {
                0:{
                    items:1
                },
                480:{
                    items:2
                },
                767:{
                    items:3
                },
                1170:{
                    items:4
                }
            }
        }); 

        /*====================================
        // testimonial-carousel
        ======================================*/


        $('.testimonial-carousel').owlCarousel({
            loop: true,
            margin: 30,
            nav: false,
            dots: true,
            autoplay: true,
            autoplayHoverPause: true,
            responsive: {
                0:{
                    items:1
                },
                480:{
                    items:1
                },
                992:{
                    items:2
                },
            }
        });

        /*=================================*/
            /* sidebar-course-carousel */
        /*=================================*/

        $('.sidebar-course-carousel').owlCarousel({
            items:1,
            loop:true,
            margin:20,
            dots:false,
            responsiveClass:true,
            navText : [
            "<i class='fa fa-long-arrow-left'></i>",
              "<i class='fa fa-long-arrow-right'></i>"
              ],
            responsive:{
                0:{
                    items:1,
                    nav:true,
                },
                
            },
            autoplay:true,
            autoplayTimeout:5000,
            autoplayHoverPause:true
        });


        /*=================================*/
            /* happy-clients */
        /*=================================*/


        $('.happy-clients').owlCarousel({
            items:5,
            loop:true,
            margin:20,
            dots:false,
            responsiveClass:true,
            responsive:{
                0:{
                    items:2,
                },
                480:{
                    items:3,
                },
                768:{
                    items:5,
                },
                
            },
            autoplay:true,
            autoplayTimeout:5000,
            autoplayHoverPause:true
        });

        /*====================================
        // counter
        ======================================*/

          $('.edu-counter .counter').counterUp({
           delay: 10,
           time: 1000
          });

        /*====================================
        // video popup
        ======================================*/

        $('.video-popup').magnificPopup({
            type: 'iframe',
            removalDelay: 300,
            mainClass: 'mfp-fade'
        });

        /*====================================
       // picture popup
       ======================================*/

        $('.image-popup').magnificPopup({
            type: 'image',
            removalDelay: 300,
            mainClass: 'mfp-fade'
        });


        /*====================================
       // inline popup
       ======================================*/

        $('.open-popup-link').magnificPopup({
            type:'inline',
            midClick: true // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
        });
        
        /*====================================
        // Isotop Active
        ======================================*/

        $(window).on('load', function() {     
            
            if ($.fn.isotope) {
                $(".isotop-active").isotope({
                    filter: '*',
                });

                    $('.gallery-nav ul li').on('click', function() {
                    $(".gallery-nav ul li").removeClass("active");
                    $(this).addClass("active");

                    var selector = $(this).attr('data-filter');
                    $(".isotop-active").isotope({
                        filter: selector,
                        animationOptions: {
                            duration: 750,
                            easing: 'easeInOutQuart',
                            queue: false,
                        }
                    });
                    return false;
                });
            }
        });

        /*=================================*/
            /* WOW */
        /*=================================*/

        new WOW().init();

        /*=================================*/
            /* search popup */
        /*=================================*/

        $('.header-search .top-search').on('click', function() { 
            $('.header-search .search-popup').toggleClass('active'); 
        }); 

        $('.ak-search .close').on('click', function() { 
            $('.search-icon .ak-search').removeClass('active'); 
        });

        $('.search-overlay').on('click', function() { 
            $('.header-search .search-popup').removeClass('active'); 
        });

        
        
        /*=================================*/
            /* toggle-nav */
        /*=================================*/
        
        $('.header-nav-search .toggle-button').on('click', function() {
            $('body').addClass('menu-active');
        });
        $('.close-icon').on('click', function() {
            $('body').removeClass('menu-active');
        });

        $('#main-navigation a').on('click', function () {
            $('#main-navigation li').removeClass('current-menu-item ');
            $(this).parents('li').addClass('current-menu-item ');
        });

        $('.site-branding').on('click', function(e){
            $('#main-navigation ul li').removeClass('current-menu-item ');
        });



        /*=================================*/
            /* goto-top */
        /*=================================*/


        $(window).scroll(function() {
            if ($(this).scrollTop() > 100) {
                $('.scroll-top').fadeIn();
            } else {
                $('.scroll-top').fadeOut();
            }
        });

        //Click event to scroll to top
        $('.scroll-top').on('click', function() {
            $('html, body').animate({ scrollTop: 0 }, 900);
            return false;
        });
    });

    function loadjscssfile(filename, filetype) {
        if (filetype == "js") {
            // if filename is a external JavaScript file
            var fileref = document.createElement('script');
            fileref.setAttribute("type","text/javascript");
            fileref.setAttribute("src", filename);
        }
        else if (filetype == "css") {
            //if filename is an external CSS file
            var fileref = document.createElement("link");
            fileref.setAttribute("rel", "stylesheet");
            fileref.setAttribute("type", "text/css")
            fileref.setAttribute("href", filename)
        }
        if (typeof fileref != "undefined") {
            document.getElementsByTagName("head")[0].appendChild(fileref)
        }
    }
})(jQuery);




