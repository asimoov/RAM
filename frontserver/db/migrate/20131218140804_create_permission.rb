class CreatePermission < ActiveRecord::Migration
  def change
    create_table :hospitals_users do |t|
      t.belongs_to :user, index: true
      t.belongs_to :hospital, index: true
    end
  end
end
