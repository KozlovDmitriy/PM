# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

# Функция построения графика на главной.
window.ready_chart = ->
  $.ajax({
    url: 'department-plans.json'
    data: ''
    success: (data, textStatus, jqXHR) ->
      Morris.Bar({
        element: 'chart',
        data: data,
        xkey: 'date',
        ykeys: ['plan', 'value'],
        labels: ['План', 'Выполнение']
      });
    dataType: 'json'
  })