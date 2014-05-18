class Analysis < ActiveRecord::Base

  attr_accessor :status

  def array
    _id = 19
    result = []
    Consultant.all.each do |consultant|
      unless ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => [1, 2, 3, 4, 5, 6]).nil?
        hash = {
            :name => consultant.short_name,
            :ind_plan => ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 1).value,
            :impl_plan => ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 2).value,
            :impl => ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 3).value,
            :av_check => ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 4).value,
            :items_count => ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 5).value,
            :total_checks_count => ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 6).value
        }
        result.push hash
      end
    end
    result
  end

  # Описание статусов
  #
  # end_analysis - Анализ завершен
  # not_end_analysis  - Анализ не завершен
  # empty_analysis - Анализ не проводился
  # full_data - Данные полностью введены
  # not_full_data - Данные введены частично
  # empty_data - Данные не введены

  def self.statuses
    %w[end_analysis not_end_analysis empty_analysis full_data not_full_data empty_data]
  end

  # Метод получения даты в виде строки.
  # @return[String] - дата анализа в виде строки.
  def str_date
    Russian::strftime(date, '%d.%m.%y')
  end

end
