
require 'roo'

# Класс чтения excel с данными для анализа.
class ReadExcel

  # Конструктор класса.
  # @param file_name [String] - Имя файла с данными.
  def initialize file_name
    @file_name = file_name
    @file = OpenOffice.new @file_name
    @file.default_sheet = @file.sheets.first

    @start_row = 15
    @end_row = 20
    @params_values = []
  end

end