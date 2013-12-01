json.array!(@medications) do |medication|
  json.extract! medication, :name, :way, :dose, :indication, :start, :end
  json.url medication_url(medication, format: :json)
end
