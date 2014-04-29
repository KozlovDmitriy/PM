# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

ready = ->

  $('#indPlan').click ->
    get_chart_array(1, 'Индивидуальный план')

  $('#impl').click ->
    get_chart_array 3, 'Выполнение плана в %'

  $('#avCheck').click ->
    get_chart_array 4, 'Значение среднего чека'

  $('#itemsCount').click ->
    get_chart_array 5, 'Количество позиций в чеке'

  $('#checksCount').click ->
    get_chart_array 6, 'Общее количество чеков'

  $('#implPlan').click ->
    window.chart_options = []
    for item in window.consultant.param_values
      if item.param_id is 1
        window.chart_options.push {
          date: item.analysis
          ind_plan: parseFloat(item.value)
        }

    for item in window.consultant.param_values
      if item.param_id is 2
        for it in window.chart_options
          if it.date is item.analysis
            it.impl_plan = parseFloat(item.value)

    show_d2_chart window.chart_options, ['Индивидуальный план', 'Выполнение плана в руб.']

window.consultant_show = ->
  window.chart_options = []
  window.consultant = {}
  consultant_id = window.location.href.match(/\d+$/)[0]

  $.ajax({
    type: 'get'
    url: "#{consultant_id}"
    dataType: 'json'
    success: (msg) ->
      window.consultant = msg

      sorting_by_date()
      get_chart_array 6, 'Общее количество чеков'

    fail: (data) ->
      console.log data
  })

sorting_by_date = ->
  for item in window.consultant.param_values
    date_options = item.analysis.split '.'
    date = new Date(parseInt(date_options[2]), parseInt(date_options[1]) - 1, parseInt(date_options[0]))
    item.analysis_date = date

  for i in [0..window.consultant.param_values.length - 1]
    for j in [0..window.consultant.param_values.length - 1]
      if window.consultant.param_values[i].analysis_date < window.consultant.param_values[j].analysis_date
        temp = window.consultant.param_values[i]
        window.consultant.param_values[i] = window.consultant.param_values[j]
        window.consultant.param_values[j] = temp



get_chart_array = (params_id, param_label) ->
  window.chart_options = []
  for item in window.consultant.param_values
    if item.param_id is params_id
      window.chart_options.push {
        date: item.analysis
      value: parseFloat(item.value)
      }

  show_chart window.chart_options, param_label

show_chart = (chart_source, param_label) ->
  $ ->
    $('#chartContainer').dxChart({
      dataSource: chart_source
      commonSeriesSettings:
        argumentField: 'date'
      tooltip:
        enabled: true
      series: [{
        name: param_label
        valueField: 'value'
      }]
    })

show_d2_chart = (chart_source, params_labels) ->
  $ ->
    $('#chartContainer').dxChart({
      dataSource: chart_source
      commonSeriesSettings:
        argumentField: 'date'
      tooltip:
        enabled: true
      series: [{
        name: params_labels[0]
        valueField: 'ind_plan'
      },
      {
        name: params_labels[1]
        valueField: 'impl_plan'
      }]
    })

$(document).ready(ready)
$(document).on('page:load', ready)