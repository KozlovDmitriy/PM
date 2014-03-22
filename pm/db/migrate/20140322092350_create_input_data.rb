class CreateInputData < ActiveRecord::Migration
  def change
    create_table :input_data do |t|
      t.datetime :date

      t.timestamps
    end
  end
end
