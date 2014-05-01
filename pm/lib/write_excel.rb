require 'rubygems'
require 'roo'

# Класс записи данных консультантов в excel файл.
class WriteExcel

  # Конструктор класса.
  # @param file_name [String] - имя файла.
  def initialize file_name
    @file_name = file_name
    @file = Roo::OpenOffice.new @file_name
    @file.default_sheet = @file.sheets.first
  end

end