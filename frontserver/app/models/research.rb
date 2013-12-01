class Research < ActiveRecord::Base
  belongs_to :hospital
  belongs_to :cause
end
