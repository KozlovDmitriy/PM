class ChangeInputDataIdToImputDatumIdInInputDataItem < ActiveRecord::Migration
  def change
    remove_column :input_data_items, :input_data_id
    add_column :input_data_items, :input_datum_id, :integer
  end
end
