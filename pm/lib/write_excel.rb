require 'odf/spreadsheet'

# Класс записи данных консультантов в excel файл.
class WriteExcel

  # Имя файла-отчета
  attr_reader :file_name

  # Конструктор класса.
  # @param consultants [Array] - список консультантов с параметрами.
  def initialize consultants
    @file_name = "#{Rails.root}/public/test.ods"
    ODF::Spreadsheet.file(@file_name) do
      table 'Результат анализа' do
        row {cell 'Анализ выполнения плана за  30 (15) дней :'}
        row {cell }
        row {cell 'Общий план'}
        row {cell '-  промежуточный план на 30(15)дней '}
        row {cell '- План-факт за 30(15) дней  '}
        row {cell 'выполнение, руб'}
        row {cell '- выполнение  %'}
        row {cell 'Конверсия'}
        row {cell 'Прирост к предыдущему месяцу ( 15, 30 дней)'}
        row {cell}
        row {cell}
        row {cell 'Консультанты'}
        row {
          cell 'ФИО'
          cell 'Инд. План, руб'
          cell 'Вып. Плана, руб.'
          cell 'Выполнение, %'
          cell 'Средний чек'
          cell 'Кол-во в чеке'
          cell 'Кол-во чеков'
          cell 'Вывод'
          cell 'Меры'
        }
        consultants.each do |item|
          row {
            item.each do |_, value|
              cell value
            end
          }
        end
      end
    end
  end

end
#
# consultants = []
# analysis = Analysis.find 19
# Consultant.all.each do |consultant|
#   unless ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => [1, 2, 3, 4, 5, 8]).nil?
#     hash = {
#         :name => consultant.short_name,
#         :ind_plan => ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => 1).value,
#         :impl_plan => ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => 2).value,
#         :impl => ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => 3).value,
#         :av_check => ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => 4).value,
#         :items_count => ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => 5).value,
#         :total_checks => ParamValue.find_by(:consultant_id => consultant.id, :date_id => analysis.id, :param_id => 6).value
#     }
#     consultants.push hash
#   end
# end
#
# c = WriteExcel.new consultants