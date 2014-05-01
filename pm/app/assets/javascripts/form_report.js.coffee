# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

ready_form_report = ->

  $('button.btn.btn-warning.form-report').click ->
    id = $(@).attr('data-analysis')
    $.ajax({
      type: 'post'
      url: 'get_report'
      data: {analysis: id}
      dataType: 'application/openoffice'
      success: (data) ->
        alert data
      fail: (data) ->
        console.log data
    })
    false

$(document).ready(ready_form_report)
$(document).on('page:load', ready_form_report)