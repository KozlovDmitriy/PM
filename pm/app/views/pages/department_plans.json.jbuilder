json.array!(@result) do |item|
  json.date item.date.str_date
  json.plan item.plan
  json.value item.value
end