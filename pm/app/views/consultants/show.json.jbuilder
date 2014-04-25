#json.extract! @consultant, :id, :family_name, :first_name, :second_name, :created_at, :updated_at, :param_values

json.id @consultant.id
json.family_name @consultant.family_name
json.first_name @consultant.first_name
json.second_name @consultant.second_name
json.param_values @consultant.param_values.each do |pv|
  json.id pv.id
  json.value pv.value
  json.param_id pv.param_id
  json.analysis Russian::strftime(pv.analysis.date, '%d.%m.%Y')
end
