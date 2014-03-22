class ParamValue < ActiveRecord::Base
  belongs_to :param

  has_and_belongs_to_many :input_data_items
end
