
# Модуль, описывающий функции для Универсальной вербально-числовой шкалы Харрингтона
module Harringthon

  # Шкала Харрингтона для числовых параметров.
  # @param max [Float] - максимальное значение параметра.
  # @param value [Float] - текущее значение параметра.
  # @return [String] - значение по шкале.
  def self.value_to_string max, value
    result = (value / max).to_f
    case result
      when result > 1.0
        'very high'
      when result <= 1.0 && result >= 0.8
        'very high'
      when result < 0.8 && result >= 0.64
        'high'
      when result < 0.64 && result >= 0.37
        'middle'
      when result < 0.37 && result >= 0.2
        'low'
      else
        'very low'
    end
  end

end