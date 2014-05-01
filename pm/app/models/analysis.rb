class Analysis < ActiveRecord::Base

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

end
