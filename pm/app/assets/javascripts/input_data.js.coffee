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

ready_form = ->

  $('tr.consultant_tr').click ->
    $.each $('tr'), (index, item) ->
      $(item).removeAttr 'class'

    tr = $(@)
    tr.attr 'class', 'info'

$(document).ready(ready_form)
$(document).on('page:load', ready_form)