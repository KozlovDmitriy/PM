class CreateAnalysisSolutionConnects < ActiveRecord::Migration
  def change
    create_table :analysis_solution_connects do |t|
      t.integer :solution_id
      t.integer :analysis_id

      t.timestamps
    end
  end
end
