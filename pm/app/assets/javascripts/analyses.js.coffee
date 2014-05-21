# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

varCompareConsultantApp = angular.module 'compareConsultantsApp', []

varCompareConsultantApp.controller 'compareConsultantsController', ($scope, $http) ->

  # Список параметров
  $scope.params = [
    {index: 1, text: 'Индивидуальный план'},
    {index: 3, text: 'Выполнение плана в %'},
    {index: 4, text: 'Значение среднего чека'},
    {index: 5, text: 'Количество позиций в чеке'},
    {index: 6, text: 'Общее количество чеков'}
  ]

  # Получение названия параметра по индексу
  $scope.get_param_label = (index) ->
    return item.text for item in $scope.params when item.index is index

  # Инициализация моделей
  $scope.left = null
  $scope.right = null
  $scope.param = null

  # Список людей
  $scope.get_consultants = ->
    promise = $http.get '/consultants.json'
    promise.success (data) ->
      $scope.consultants = data
    promise.error (data) ->
      console.log data

  # Получение консультанта по id
  $scope.get_consultant = (id) ->
    result = item for item in $scope.consultants when "#{item.id}" == "#{id}"
    return result

  # Построение графика
  $scope.change = ->
    if !$scope.left? or !$scope.right or !$scope.param
      return
    promise = $http.get "/consultants/#{$scope.left}.json"
    promise.success (data) ->
      window.consultant = data
      sorting_by_date()
      $scope.left_consultant = window.consultant
      result = $http.get "/consultants/#{$scope.right}.json"
      result.success (data) ->
        window.consultant = data
        sorting_by_date()
        $scope.right_consultant = window.consultant
        window.chart_options = []
        for item in $scope.left_consultant.param_values
          if parseInt(item.param_id) is parseInt($scope.param)
            window.chart_options.push {
              date: item.analysis
              value1: parseFloat(item.value)
            }
        index = 0
        for item in $scope.right_consultant.param_values
          if parseInt(item.param_id) is parseInt($scope.param)
            window.chart_options[index].value2 = parseFloat(item.value)
            index++
        $scope.left_fio = $scope.get_consultant($scope.left).short_name
        $scope.right_fio = $scope.get_consultant($scope.right).short_name
        $scope.table_values = window.chart_options
        $ ->
          $('#ch').html ''
          $('#ch').append('<div id="chart">')
          $('#chart').dxChart({
            dataSource: window.chart_options
            commonSeriesSettings:
              argumentField: 'date'
            tooltip:
              enabled: true
            series: [{
              name: $scope.left_fio
              valueField: 'value1'
            },{
              name: $scope.right_fio
              valueField: 'value2'
            }]
          })
      result.error (data) ->
        console.log data
    promise.error (data) ->
      console.log data


  $scope.get_consultants()