class AddConsultantIdToAnalysisSolutionConnects < ActiveRecord::Migration
  def change
    add_column :analysis_solution_connects, :consultant_id, :integer
  end
end
