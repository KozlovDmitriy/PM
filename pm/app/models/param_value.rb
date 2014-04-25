class ParamValue < ActiveRecord::Base
  belongs_to :param

  belongs_to :input_data_item

  def analysis
    Analysis.find date_id
  end

end
