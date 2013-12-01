json.array!(@causes) do |cause|
  json.extract! cause, :disease, :cid_id, :research_id
  json.url cause_url(cause, format: :json)
end
