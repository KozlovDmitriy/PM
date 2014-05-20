# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

varConfigApp = angular.module 'configApp', []

varConfigApp.controller 'configController', ($scope, $http) ->

  # Идентификатор решателя.
  $scope.reasoner_id = 1

  # Индикатор результата.
  $scope.result = null

  # Метод загрузки значения порта решателя.
  $scope.getReasonerPortValue = ->
    promise = $http.get "/configures/#{1}.json"
    promise.success (data) ->
      $scope.reasoner_port = data.value
    promise.error (data) ->
      console.log data

  # Сохранение данных
  $scope.save = ->
    obj =
      reasoner_id: $scope.reasoner_id
      reasoner_value: $scope.reasoner_port
    promise = $http(
      url: '/configures'
      method: 'POST'
      params: obj
    )
    promise.success (data) ->
      $scope.result = true
    promise.error (data) ->
      $scope.result = false

  $scope.getReasonerPortValue()