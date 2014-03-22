class InputDataItem < ActiveRecord::Base
  belongs_to :input_datum
  belongs_to :consultant

  has_and_belongs_to_many :param_values
end
