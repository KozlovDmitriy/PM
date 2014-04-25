json.array!(@param_values) do |param_value|
  json.extract! param_value, :id, :value, :param_id, :analysis
  json.url param_value_url(param_value, format: :json)
end
