json.array!(@users) do |user|
  json.extract! user, :id, :username, :email, :encrypted_password, :salt, :name
  json.url user_url(user, format: :json)
end
