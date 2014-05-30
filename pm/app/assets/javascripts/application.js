// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or vendor/assets/javascripts of plugins, if any, can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// compiled file.
//
// Read Sprockets README (https://github.com/sstephenson/sprockets#sprockets-directives) for details
// about supported directives.
//
//= require jquery
//= require jquery_ujs
//= require turbolinks
//= require bootstrap
//= require docs.min
//= require globalize
//= require dx.chartjs
//= require jquery-fileupload/vendor/jquery.ui.widget
//= require jquery-fileupload/jquery.iframe-transport
//= require jquery-fileupload/jquery.fileupload
//= require angular.min
//= require_tree .
//= require turbolinks

// Функция отлова события перемещения окна
var changeScrollPosition = function() {

    positionY = window.pageYOffset;

    if (positionY > 0) {
        $('#goTop').show();
    } else {
        $('#goTop').hide();
    }
};

$(document).ready(function(){
    $('#goTop').hide();

    $('#goTop a').click(function(){

        $('html, body').animate({
            scrollTop: $('body').offset().top
        }, 900);

        return false;
    });
});

// Подключение обработчика
window.addEventListener('scroll', changeScrollPosition, false);