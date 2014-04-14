class CreateAnalysisProblemConnects < ActiveRecord::Migration
  def change
    create_table :analysis_problem_connects do |t|
      t.integer :problem_id
      t.integer :analysis_id

      t.timestamps
    end
  end
end
