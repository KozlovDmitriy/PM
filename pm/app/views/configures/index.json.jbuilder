json.array!(@configures) do |configure|
  json.extract! configure, :id, :key, :value
  json.url configure_url(configure, format: :json)
end
