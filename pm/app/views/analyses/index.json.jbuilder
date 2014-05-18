json.array!(@analyses) do |analysis|
  json.extract! analysis, :id, :date
  json.url analysis_url(analysis, format: :json)
  json.str_date analysis.str_date
end
