class CreateCases < ActiveRecord::Migration
  def change
    create_table :cases do |t|
      t.float :ind_plan
      t.float :impl_plan
      t.float :impl
      t.float :av_check
      t.float :items_count
      t.integer :total_checks
      t.string :problems_uri
      t.string :problems_text
      t.string :solutions_uri
      t.string :solutions_text
      t.string :uri

      t.timestamps
    end
  end
end
