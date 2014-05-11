# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

newSolutionApp = angular.module 'newSolutionApp', []

newSolutionApp.controller 'newSolutionController', ($scope, $http) ->

  # Отображение кнопки редактора
  $scope.isShowRedactorBtn = false

  # Список рекомендаций
  $scope.solutions = []

  # Метод чтения всех рекомендаций из онтологии
  $scope.allSolutions = ->
  	promise = $http.get '/solutions.json'
  	promise.success (data) ->
  		$scope.solutions = []
  		$scope.solutions = data
  	promise.error (data) ->
  		console.log data

  $scope.allSolutions()

  # Добавление рекомендации в описание
  $scope.addSolution = ->
    val = $scope.solution.description if $scope.solution.description?
    val = '' unless $scope.solution.description?
    $scope.solution.description = "#{val} #{$scope.currentSolution} И"

  # Метод изменения типа рекомендации
  $scope.changeType = ->
    if $scope.solution.type is 'complex'
      $scope.isShowRedactorBtn = true
    else
      $scope.isShowRedactorBtn = false
