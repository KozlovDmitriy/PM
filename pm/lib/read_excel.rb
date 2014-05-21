
require 'rubygems'
require 'roo'

# Класс чтения excel с данными для анализа.
class ReadExcel

  # Конструктор класса.
  # @param file_name [String] - Имя файла с данными.
  def initialize file_name
    @file_name = file_name
    @file = Roo::OpenOffice.new file_name
    @file.default_sheet = @file.sheets.first

    @start_row = 15
    @end_row = 20
    @params_values = []
  end

  # Метод получения параметров из файла.
  # @return [Array] - массив значений параметров.
  def get_params
    read_excel
  end

  private

  # Метод чтения файла.
  # @return [Array] - массив значений параметров.
  def read_excel
    @start_row.upto(@end_row) do |line|
      fio = @file.cell line, 'A'
      ind_plan = @file.cell line, 'C'
      impl_plan = @file.cell line, 'D'
      impl = @file.cell line, 'E'
      av_check = @file.cell line, 'F'
      items = @file.cell line, 'G'
      total_checks = @file.cell line, 'H'
      @params_values.push({:fio => fio, :ind_plan => ind_plan, :impl_plan => impl_plan, :impl => impl,
                           :av_check => av_check, :items => items, :total_checks => total_checks})
    end
    @params_values
  end

end