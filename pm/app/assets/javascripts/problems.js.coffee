# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

newProblemApp = angular.module 'newProblemApp', []
newProblemApp.controller 'newProblemController', ($scope, $http) ->

  # Отображение кнопки рекдактора.
  $scope.isShowRedactorBtn = false

  # Список проблем
  $scope.problems = []

  # Метод чтения всех проблем из онтологии.
  $scope.allProblems = ->
    promise = $http.get '/problems.json'
    promise.success (data) ->
      $scope.problems = []
      $scope.problems = data
    promise.error (data) ->
      console.log data

  $scope.allProblems()

  # Добавление проблемы в описание
  $scope.addProblem = ->
    val = $scope.problem.description if $scope.problem.description?
    val = '' unless $scope.problem.description?
    $scope.problem.description = "#{val} #{$scope.currentProblem} И"

  # Метод изменения типа проблемы.
  $scope.changeType = ->
    if $scope.problem.type is 'complex'
      $scope.isShowRedactorBtn = true
    else
      $scope.isShowRedactorBtn = false