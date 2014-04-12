class AddFieldsToParamValues < ActiveRecord::Migration
  def change
    add_column :param_values, :date_id, :integer
    add_column :param_values, :consultant_id, :integer
  end
end
