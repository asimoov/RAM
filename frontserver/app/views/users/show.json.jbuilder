json.extract! @user, :id, :name, :email, :admin, :created_at, :updated_at
json.hospitals @user.hospitals do |hospital|
  json.id hospital.id
  json.name hospital.name
  json.acronym hospital.acronym
  json.country hospital.country
  json.created_at hospital.created_at
  json.updated_at hospital.updated_at
end
