class AddTypeToSolution < ActiveRecord::Migration
  def change
    add_column :solutions, :solution_type, :string
  end
end
