class CreateAnalyses < ActiveRecord::Migration
  def change
    create_table :analyses do |t|
      t.datetime :date

      t.timestamps
    end
  end
end
