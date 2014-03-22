class CreateParams < ActiveRecord::Migration
  def change
    create_table :params do |t|
      t.string :name
      t.string :type
      t.string :input_type
      t.string :label
      t.string :excel_label
      t.string :owl_class
      t.string :local_similarity

      t.timestamps
    end
  end
end
