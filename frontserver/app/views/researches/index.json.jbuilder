json.array!(@researches) do |research|
  json.extract! research, :handbook, :name, :cns, :sex, :weight, :height, :color, :unit, :bed, :admission, :hospital_id, :cause_id
  json.url research_url(research, format: :json)
end
