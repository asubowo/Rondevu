json.array!(@events) do |event|
  json.extract! event, :id, :title, :description, :host, :owner_id, :public, :location, :city, :date_time, :capacity, :category, :attending
  json.url event_url(event, format: :json)
end
