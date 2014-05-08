class Solution < ActiveRecord::Base

  attr_accessor :solution_types
  
  def self.types
    %w[simple complex]
  end

end
