class AddAnalysisStatusToAnalysis < ActiveRecord::Migration
  def change
    add_column :analyses, :status, :string
  end
end
