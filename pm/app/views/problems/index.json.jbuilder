json.array!(@problems) do |problem|
  json.extract! problem, :id, :description
  json.url problem_url(problem, format: :json)
end
