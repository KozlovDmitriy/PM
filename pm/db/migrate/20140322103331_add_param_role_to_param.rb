class AddParamRoleToParam < ActiveRecord::Migration
  def change
    add_column :params, :role, :string
  end
end
