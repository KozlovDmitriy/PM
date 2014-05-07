class Problem < ActiveRecord::Base

  attr_accessor :type
  def self.types
    %w[simple complex]
  end

end
