json.array!(@params) do |param|
  json.extract! param, :id, :name, :type, :input_type, :label, :excel_label, :owl_class, :local_similarity
  json.url param_url(param, format: :json)
end
