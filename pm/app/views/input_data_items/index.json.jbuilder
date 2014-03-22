json.array!(@input_data_items) do |input_data_item|
  json.extract! input_data_item, :id, :consultant_id, :input_data_id
  json.url input_data_item_url(input_data_item, format: :json)
end
