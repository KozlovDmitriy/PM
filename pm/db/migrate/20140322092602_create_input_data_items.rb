class CreateInputDataItems < ActiveRecord::Migration
  def change
    create_table :input_data_items do |t|
      t.integer :consultant_id
      t.integer :input_data_id

      t.timestamps
    end
  end
end
