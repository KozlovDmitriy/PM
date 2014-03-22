class CreateParamValues < ActiveRecord::Migration
  def change
    create_table :param_values do |t|
      t.string :value
      t.integer :param_id

      t.timestamps
    end
  end
end
