# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

# Подгрузка файла.
$(document).ready ->
  $(->
    $('#file').fileupload(
      dataType: 'json',
      url: '/upload/excel.json'
      done: (e, data) ->
        console.log data.result
    )
  )

ready_form = ->

  $('tr.analyse_consultant_tr').click ->
    $.each $('tr'), (index, item) ->
      $(item).removeAttr 'class'

    tr = $(@)
    tr.attr 'class', 'info'

$(document).ready(ready_form)
$(document).on('page:load', ready_form)

appInputData = angular.module('inputData', [])

appInputData.controller 'inputDataController', ($scope, $http) ->

  # Текущий консультант.
  $scope.currentConsultant = null

  # Список всех консультантов
  $scope.consultants = []

  $scope.changeCurrentConsultant = (item) ->
    angular.forEach $scope.consultants, (c) ->
      c.class = ''
    item.class = 'info'
    console.log item
    $scope.currentConsultant = item

  # Функция получения консультантов.
  $scope.loadConsultants = ->
    promise = $http.get('/consultants.json')
    promise.success (data) ->
      for item in data
        hash =
          id: item.id
          fio: "#{item.family_name} #{item.first_name} #{item.second_name}"
          url: item.url
          main:
            indPlan: 0,
            implPlan: 0,
            impl: 0,
            avCheck: 0,
            itemsCount: 0,
            totalChecks: 0
          additional:
            holiday: false,
            hospital: false,
            mleave: false,
            exp: false,
            dismissal: false
        $scope.consultants.push hash
    promise.error (data) ->
      console.log data

  $scope.loadConsultants()