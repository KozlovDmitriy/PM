
# Класс json запроса к серверу.
class JsonRequest

  attr_accessor :code, # Код запроса
                :body  # Тело запроса

  # Конструктор
  # @param code [Integer] - код запроса
  # @param body [Hash] - тело запроса
  def initialize code, body
    @code = code.to_s
    @body = body.to_s
  end

  # Преобразование объекта в json-строку
  def to_json
    json = {:code => code, :body => body}
    require 'json'
    json.to_json
  end

  # Преобразования json-строки в объект.
  def from_json json_string
    require 'json'
    json = JSON.parse json_string
    @code = json['code']
    @body = json['body']
  end

end