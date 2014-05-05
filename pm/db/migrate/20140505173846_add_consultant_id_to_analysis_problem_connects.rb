class AddConsultantIdToAnalysisProblemConnects < ActiveRecord::Migration
  def change
    add_column :analysis_problem_connects, :consultant_id, :integer
  end
end
