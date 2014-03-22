class AddSortingToParams < ActiveRecord::Migration
  def change
    add_column :params, :sorting, :integer
  end
end
