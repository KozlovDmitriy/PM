# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

jQuery(document).ready ->
  jQuery('#do_analyse').click ->
    impl_plan = jQuery('#impl_plan').val()
    av_check = jQuery('#av_check').val()
    items_count = jQuery('#items_count').val()
    total_checks_count = jQuery('#total_checks_count').val()
    params = # Параметры запроса
      impl_plan: impl_plan
      av_check: av_check
      items_count: items_count
      total_checks_count: total_checks_count

    # Отправка запроса.
    jQuery.ajax(
      url: '/analyse/problems.json'
      type: 'POST'
      data: params
      dataType: 'json'
    ).done (response) ->
      console.log(response)
      jQuery('#problems').html('')
      jQuery('#problems_uri').val(response.uri)
      for item in response.value
        jQuery('#problems').append("<li class='list-group-item-warning'>#{item}</li>")

      problems =
        problems: response.uri
      jQuery.ajax(
        url: '/analyse/solutions.json'
        type: 'POST'
        data: problems
        dataType: 'json'
      ).done (result) ->
        console.log(result)
        jQuery('#solutions').html('')
        for item in result.value
          jQuery('#solutions').append "<li class='list-group-item-danger'>#{item}</li>"
        jQuery('.alert#alert').append "<div class=\"alert alert-success alert-dismissable\">
          <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>
          <strong>Ура!</strong> Анализ успешно завершен.</div>"
    false


ready_form_analyse = ->

  $('tr.consultant_tr').click ->
    $.each $('tr'), (index, item) ->
      $(item).removeAttr 'class'

    tr = $(@)
    tr.attr 'class', 'info'

$(document).ready(ready_form_analyse)
$(document).on('page:load', ready_form_analyse)