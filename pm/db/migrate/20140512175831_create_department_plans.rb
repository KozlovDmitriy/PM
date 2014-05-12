class CreateDepartmentPlans < ActiveRecord::Migration
  def change
    create_table :department_plans do |t|
      t.integer :date_id
      t.integer :plan
      t.integer :value

      t.timestamps
    end
  end
end
