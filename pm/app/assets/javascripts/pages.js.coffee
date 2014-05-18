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

showDepChartApp = angular.module 'showDepChartApp', []
showDepChartApp.controller 'showDepChartController', ($scope, $http) ->

  # Левая граница
  $scope.left_date = null

  # Правая граница
  $scope.right_date = null

  # Список анализов
  $scope.analyses = []

  # Данные таблицы
  $scope.chart_data = [{date: '01.01.01', plan: 1, value: 1}]

  # Диаграмма
  $scope.chart = Morris.Bar({
    element: 'chart',
    data: $scope.chart_data,
    xkey: 'date',
    ykeys: ['plan', 'value'],
    labels: ['План', 'Выполнение']
  });

  # Метод получения всех анализов.
  $scope.get_all_analyse_date = ->
    promise = $http.get 'analyses.json'
    promise.success (data) ->
      $scope.analyses = data
    promise.error (data) ->
      console.log data

  # Отображение диаграммы при смене дат.
  $scope.showChart = ->
    if $scope.left_date? and $scope.right_date?
      promise = $http({
        url: 'chart.json'
        method: 'get'
        params: { first: $scope.left_date, last: $scope.right_date}
      })
      promise.success (data) ->
        if data? and data.length != 0
          $scope.chart_data = data
        $scope.chart.setData $scope.chart_data
      promise.error (data) ->
        console.log data

  $scope.get_all_analyse_date()
