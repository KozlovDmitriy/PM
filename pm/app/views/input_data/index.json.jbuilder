json.array!(@input_data) do |input_datum|
  json.extract! input_datum, :id, :date
  json.url input_datum_url(input_datum, format: :json)
end
