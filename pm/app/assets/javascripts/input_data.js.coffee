# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

# Подгрузка файла.
$(document).ready ->
  $(->
    $('#file').fileupload(
      dataType: 'json',
      url: '/upload/excel.json'
      done: (e, data) ->
        console.log data.result
    )
  )
