class ChangeDepartmentPlanType < ActiveRecord::Migration
  def self.up
    change_table :department_plans do |t|
      t.change :plan, :float
    end
  end
  def self.down
    change_table :department_plans do |t|
      t.change :plan, :integer
    end
  end
end
