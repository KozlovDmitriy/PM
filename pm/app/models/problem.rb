class Problem < ActiveRecord::Base

  def self.types
    %w[simple complex]
  end

end
