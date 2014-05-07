class AddTypeToProblems < ActiveRecord::Migration
  def change
    add_column :problems, :problem_type, :string
  end
end
