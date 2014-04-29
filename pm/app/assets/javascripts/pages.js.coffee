# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

window.ready_chart = ->
  Morris.Bar({
    element: 'chart',
    data: [
      { y: '01.01.2014', a: 12000, b: 10756 },
      { y: '15.01.2014', a: 12000,  b: 15234 },
      { y: '01.02.2014', a: 17000,  b: 10671 },
    ],
    xkey: 'y',
    ykeys: ['a', 'b'],
    labels: ['Series A', 'Series B']
  });