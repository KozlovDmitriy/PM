json.array!(@cases) do |case|
  json.extract! case, :id, :ind_plan, :impl_plan, :impl, :av_check, :items_count, :total_checks, :problems_uri, :problems_text, :solutions_uri, :solutions_text, :uri
  json.url case_url(case, format: :json)
end
