class CreateCids < ActiveRecord::Migration
  def change
    create_table :cids do |t|
      t.string :code
      t.string :description

      t.timestamps
    end
  end
end
