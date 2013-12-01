class CreateCauses < ActiveRecord::Migration
  def change
    create_table :causes do |t|
      t.string :disease
      t.references :cid, index: true
      t.references :research, index: true

      t.timestamps
    end
  end
end
