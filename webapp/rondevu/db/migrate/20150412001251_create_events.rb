class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.string :title
      t.text :description
      t.string :host
      t.integer :owner_id
      t.boolean :public
      t.string :location
      t.string :city
      t.datetime :date_time
      t.integer :capacity
      t.string :category
      t.integer :attending

      t.timestamps
    end
  end
end
