# Place all the behaviors and hooks related to the matching controller here.
# All this logic will automatically be available in application.js.
# You can use CoffeeScript in this file: http://coffeescript.org/

# Загружаем визуализационный API
# в нем используем пакет 'corechart'
google.load 'visualization', '1', {
  packages: ['corechart']
}

# Определяем функцию для отрисовки диаграммы
draw_chart = ->
  # Создаем таблицу с данными (изначально пустую).
  data = new google.visualization.DataTable()
  # Определяем столбцы таблицы.
  data.addColumn 'string', 'Date'
  data.addColumn 'number', 'Установленнный план'
  data.addColumn 'number', 'Фактический план'
  # Указываем количество строк в таблице.
  data.addRows(3)
  # Теперь добавляем данные в каждую ячейку таблицы.

  # Строка 0.
  data.setValue 0, 0, '01.01.2014'
  data.setValue 0, 1, 12000
  data.setValue 0, 2, 10756

  # Строка 1.
  data.setValue 1, 0, '15.01.2014'
  data.setValue 1, 1, 12000
  data.setValue 1, 2, 15234

  # Строка 2.
  data.setValue 2, 0, '01.02.2014'
  data.setValue 2, 1, 17000
  data.setValue 2, 2, 10671

  # Находим на странице элемент, в котором будем
  # отрисовывать диаграмму.
  chart_element = document.getElementById 'chart'
  # Создаем объект диаграммы.
  chart = new google.visualization.ColumnChart chart_element
  # Рисуем!
  chart.draw data, {
    with: 800,
    height: 400,
    title: 'План продаж',
    vAxis: {
      minValue: 0,
      maxValue: 100
    }
  }

# Ожидаем, пока произойдет событие загрузки API,
# затем отрисовываем диаграмму.
google.setOnLoadCallback draw_chart