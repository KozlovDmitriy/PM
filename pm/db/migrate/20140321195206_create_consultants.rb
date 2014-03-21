class CreateConsultants < ActiveRecord::Migration
  def change
    create_table :consultants do |t|
      t.string :family_name
      t.string :first_name
      t.string :second_name

      t.timestamps
    end
  end
end
