json.array!(@event_users) do |event_user|
  json.extract! event_user, :id, :event_id, :user_id
  json.url event_user_url(event_user, format: :json)
end
