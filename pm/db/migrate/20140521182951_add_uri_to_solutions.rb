class AddUriToSolutions < ActiveRecord::Migration
  def change
    add_column :solutions, :uri, :string
  end
end
