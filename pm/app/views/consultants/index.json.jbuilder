json.array!(@consultants) do |consultant|
  json.extract! consultant, :id, :family_name, :first_name, :second_name
  json.url consultant_url(consultant, format: :json)
end
