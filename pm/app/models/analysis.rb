class Analysis < ActiveRecord::Base

  attr_accessor :status

  def array
    _id = self.id
    result = []
    Consultant.all.each do |consultant|
      unless ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => [1, 2, 3, 4, 5, 6]).nil?
        problems = []
        AnalysisProblemConnect.where(:analysis_id => _id, :consultant_id => consultant.id).each { |it| problems.push it.problem.description }
        solutions = []
        AnalysisSolutionConnect.where(:analysis_id => _id, :consultant_id => consultant.id).each { |it| solutions.push it.solution.description }
        hash = {}
        hash[:name] = consultant.short_name
        ind_plan = ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 1)
        hash[:ind_plan] = ind_plan.nil? ? nil : ind_plan.value
        impl_plan = ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 2)
        hash[:impl_plan] = impl_plan.nil? ? nil : impl_plan.value
        impl = ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 3)
        hash[:impl] = impl.nil? ? nil : impl.value
        av_check = ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 4)
        hash[:av_check] = av_check.nil? ? nil : av_check.value
        items_count = ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 5)
        hash[:items_count] = items_count.nil? ? nil : items_count.value
        total_checks_count = ParamValue.find_by(:consultant_id => consultant.id, :date_id => _id, :param_id => 6)
        hash[:total_checks_count] = total_checks_count.nil? ? nil : total_checks_count.value
        hash[:problems] = problems.uniq
        hash[:solutions] = solutions.uniq
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
