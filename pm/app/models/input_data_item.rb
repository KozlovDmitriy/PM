class InputDataItem < ActiveRecord::Base
  belongs_to :input_datum
  belongs_to :consultant
end
