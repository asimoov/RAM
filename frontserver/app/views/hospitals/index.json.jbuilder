json.array!(@hospitals) do |hospital|
  json.extract! hospital, :name, :acronym, :country
  json.url hospital_url(hospital, format: :json)
end
