class CreateMedications < ActiveRecord::Migration
  def change
    create_table :medications do |t|
      t.string :name
      t.string :way
      t.string :dose
      t.string :indication
      t.date :start
      t.date :end

      t.timestamps
    end
  end
end
