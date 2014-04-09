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
      for item in response
        jQuery('#problems').append("<li class='list-group-item-warning'>#{item}</li>")

    false