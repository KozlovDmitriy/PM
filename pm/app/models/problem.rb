class Problem < ActiveRecord::Base

  # attr_accessor :problem_type
  def self.types
    %w[simple complex]
  end

  # Метод удаления лишнего И из описания комплексной проблемы.
  def cut_description!
  	if self.problem_type == 'complex'
  		self.description = self.description.gsub /\s[И]$/, ''
  	end
  end

end
