class CreateResearches < ActiveRecord::Migration
  def change
    create_table :researches do |t|
      t.string :handbook
      t.string :name
      t.string :cns
      t.string :sex
      t.float :weight
      t.float :height
      t.string :color
      t.string :unit
      t.integer :bed
      t.date :admission
      t.references :hospital, index: true
      t.references :cause, index: true

      t.timestamps
    end
  end
end
