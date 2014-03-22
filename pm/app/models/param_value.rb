class ParamValue < ActiveRecord::Base
  belongs_to :param

  belongs_to :input_data_item
end
