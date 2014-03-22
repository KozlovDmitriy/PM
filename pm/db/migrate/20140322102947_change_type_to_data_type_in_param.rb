class ChangeTypeToDataTypeInParam < ActiveRecord::Migration
  def change
    remove_column :params, :type
    add_column :params, :data_type, :string
  end
end
