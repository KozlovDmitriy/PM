
# Класс json запроса к серверу.
class JsonRequest

  attr_accessor :code, # Код запроса
                :params,
                :problems,
                :solutions

  # Конструктор
  # @param code [Integer] - код запроса
  # @param body [Hash] - тело запроса
  def initialize code, params, problems, solutions
    @code = code
    @params = params
    @problems = problems
    @solutions = solutions
  end

  # Преобразование объекта в json-строку
  def to_json
    json = {:code => @code, :params => @params, :problems => @problems, :solutions => @solutions}
    require 'json'
    json.to_json
  end

  # Преобразования json-строки в объект.
  def from_json json_string
    require 'json'
    json = JSON.parse json_string
    @code = json['code']
    @params = json['params']
    @problems = json['problems']
    @solutions = json['solutions']
  end

end