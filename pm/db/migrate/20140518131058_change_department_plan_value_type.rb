class ChangeDepartmentPlanValueType < ActiveRecord::Migration
  def self.up
    change_table :department_plans do |t|
      t.change :value, :float
    end
  end
  def self.down
    change_table :department_plans do |t|
      t.change :value, :integer
    end
  end
end
