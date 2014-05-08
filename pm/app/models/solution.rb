class Solution < ActiveRecord::Base

  attr_accessor :solution_type
  
  def self.types
    %w[simple complex]
  end

end
