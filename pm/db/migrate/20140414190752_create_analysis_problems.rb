class CreateAnalysisProblems < ActiveRecord::Migration
  def change
    create_table :analysis_problems do |t|
      t.integer :problem_id

      t.timestamps
    end
  end
end
