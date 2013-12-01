json.array!(@cids) do |cid|
  json.extract! cid, :code, :description
  json.url cid_url(cid, format: :json)
end
