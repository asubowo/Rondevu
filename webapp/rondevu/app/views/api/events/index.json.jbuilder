json.events @events do |event|
  json.title    event.title
  json.desciption event.description
  json.public event.public
  json.host event.host
  json.owner_id event.owner_id
  json.public event.public
  json.location event.location
  json.city event.city
  json.date_time event.date_time
  json.capacity event.capacity
  json.category event.category
  json.attending event.attending

  #json. event.user ? event.user.id : nil
end
