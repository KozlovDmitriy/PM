class Problem < ActiveRecord::Base

  # attr_accessor :problem_type
  def self.types
    %w[simple complex]
  end

  # Метод формирования hash.
  def to_hash
    {:id => id, :description => description, :problem_type => problem_type, :uri => uri}
  end

  # Метод удаления лишнего И из описания комплексной проблемы.
  def cut_description!
  	if self.problem_type == 'complex'
  		self.description = self.description.gsub /\s[И]$/, ''
  	end
  end

  # Метод получения типа проблемы на русском.
  def self.get_russian_type type
    case type
      when 'simple'
        return 'Простая'
      when 'complex'
        return 'Сложная'
      else
        return 'Другая'
    end
  end

  # Метод получения типа проблемы на русском.
  def get_russian_type
    case problem_type
      when 'simple'
        return 'Простая'
      when 'complex'
        return 'Сложная'
      else
        return 'Другая'
    end
  end

end
