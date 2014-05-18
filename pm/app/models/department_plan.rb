class DepartmentPlan < ActiveRecord::Base
	belongs_to :date, class_name: 'Analysis', foreign_key: 'date_id'
end
