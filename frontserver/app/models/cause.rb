class Cause < ActiveRecord::Base
  belongs_to :cid
  belongs_to :research
end
