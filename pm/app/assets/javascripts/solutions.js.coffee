# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

editSolutionApp = angular.module 'editSolutionApp', []
editSolutionApp.controller 'editSolutionController', ($scope, $http) ->

  # Идентификатор текщей рекомендмции.
  $scope.id = window.location.href.match(/\/\d+\//)[0].replace /\//g, ''

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
    val = "#{$scope.solution.description} И" if $scope.solution.description?
    val = '' unless $scope.solution.description?
    $scope.solution.description = "#{val} #{$scope.currentSolution} И"

  # Метод изменения типа рекомендации
  $scope.changeType = ->
    if $scope.solution.type is 'complex'
      $scope.isShowRedactorBtn = true
    else
      $scope.isShowRedactorBtn = false

  # Метод получения рекомендации.
  $scope.get_solution = ->
    promise = $http.get "/solutions/#{$scope.id}.json"
    promise.success (data) ->
      console.log data
      $scope.solution = {}
      $scope.solution.description = data.description
      $scope.solution.type = data.solution_type
    promise.error (data) ->
      console.log data

  $scope.get_solution()

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
