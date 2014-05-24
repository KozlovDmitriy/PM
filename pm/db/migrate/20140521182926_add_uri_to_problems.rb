class AddUriToProblems < ActiveRecord::Migration
  def change
    add_column :problems, :uri, :string
  end
end
