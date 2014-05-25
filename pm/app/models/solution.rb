class Solution < ActiveRecord::Base

  # attr_accessor :solution_type
  
  def self.types
    %w[simple complex]
  end

  # Метод преобразования объекта в hash
  def to_hash
    {:id => id, :description => description, :solution_type => solution_type, :uri => uri}
  end

  # Удаляет последнюю букву И у комплексных рекомендаций.
  def cut_description!
  	if self.solution_type == 'complex'
  		self.description = self.description.gsub /\s[И]$/, ''
  	end
  end

  # Метод получения типа рекомендации на русском.
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

  # Метод получения типа рекомендации на русском.
  def get_russian_type
    case solution_type
      when 'simple'
        return 'Простая'
      when 'complex'
        return 'Сложная'
      else
        return 'Другая'
    end
  end

end
