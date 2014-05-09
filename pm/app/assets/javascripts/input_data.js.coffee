# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

# Подгрузка файла.
$(document).ready ->
  $(->
    $('#file').fileupload(
      dataType: 'json',
      url: '/upload/excel.json'
      progressall: (e, data) ->
        $('#progressBar').show()
      done: (e, data) ->
#        console.log data.result
        window.ods = data.result
        $('#progressBar').hide()
        alert 'Данные успешно загружены'
        window.odsRead()
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

  # Закончен ли ввод данных.
  $scope.isFinish = false

  # Отображение алерта
  $scope.isProcess = false

  # Список всех консультантов
  $scope.consultants = []

  # Загрузка отчета.
  $scope.uploadOds = ->
    $scope.consultants = []
    $scope.consultants = window.ods
    $scope.$apply()

  window.odsRead = $scope.uploadOds

  # Сохранение данны и переход к анализу.
  $scope.saveAndAnalyse = ->
    $scope.isFinish = true
    $scope.save()

  # Метод сохранения данных.
  $scope.save = ->
    $scope.isProcess = true
    document.body.scrollTop = document.documentElement.scrollTop = 0
    obj =
      date: new Date()
      isFinish: $scope.isFinish
      params:
        $scope.consultants
    console.log obj
    promise = $http.post '/save_params.json', obj
    promise.success (data) ->
      console.log data
      $scope.isProcess = false
      alert 'Сохранение данных прошло успешно!'
      window.location.href = '/new-analyse' if $scope.isFinish is true
    promise.error (data) ->
      console.log data
      $scope.isProcess = false
      alert 'Ошибка сохранения данных!'

  # Функция смены текущего консультанта.
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