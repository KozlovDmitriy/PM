class Solution < ActiveRecord::Base

  # attr_accessor :solution_type
  
  def self.types
    %w[simple complex]
  end

  # Удаляет последнюю букву И у комплексных рекомендаций.
  def cut_description!
  	if self.solution_type == 'complex'
  		self.description = self.description.gsub /\s[И]$/, ''
  	end
  end

end
